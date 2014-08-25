package com.acsys.account.action;

import java.util.Map;

import javax.annotation.Resource;

import com.acsys.account.model.User;
import com.acsys.account.service.IUserService;
import com.acsys.common.Constants;
import com.acsys.common.Utils;
import com.acsys.common.cookie.CookieSpecification;
import com.acsys.common.cookie.CookieUtils;
import com.acsys.core.BaseAction;
import com.acsys.core.CommonContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class LoginAction extends BaseAction implements Preparable {
	private static final long serialVersionUID = 6436061852091047456L;
	private String email;
	private String password;
	private boolean rememberMe;
	private User eUser;
	private User currUser;
	private Map session;
	private String uiError;

	// Spring Inject
	// private IUserService userService = (IUserService) SpringContext.getBean("userService");
	@Resource
	private IUserService userService;
	@Resource
	private CookieUtils cookieUtils;

	@Override
	public void prepare() throws Exception {
		session = ActionContext.getContext().getSession();
		eUser = null;
		currUser = (User) session.get(Constants.USER_SESSION);
	}

	@Override
	public void validate() {
		if (Utils.isEmpty(email)) {
			addFieldError("email", "E-Mail is required.");
			uiError += "email,";
		}
		if (Utils.isEmpty(password)) {
			addFieldError("password", "Password is required.");
			uiError += "password,";
		}
		if (Utils.isEmpty(uiError)) {
			eUser = userService.getUserByEmail(email);
			if (Utils.isEmpty(eUser)) {
				addFieldError("email", "The user does not exist.");
				uiError += "email,";
			}
		}
	}

	@Override
	public String input() {
		getEmailFromCookie();
		getAutoLoginFromCookie();
		if (isUserLogined()) {
			eUser = currUser;
			return SUCCESS;
		}
		if (rememberMe) {
			if (!Utils.isEmpty(email) && !Utils.isEmpty(password)) {
				eUser = userService.getUserByEmail(email);
				if (!Utils.isEmpty(eUser) && password.equals(eUser.getPassword())) {
					session.put(Constants.USER_SESSION, eUser);
					CommonContext.setCurrentUser(eUser);
					currUser = eUser;
					return SUCCESS;
				}
			}
		}
		return INPUT;
	}

	public String submit() {
		password = Utils.MD5(password);
		if (password.equals(eUser.getPassword())) {
			session.put(Constants.USER_SESSION, eUser);
			CommonContext.setCurrentUser(eUser);
			currUser = eUser;
			setEmailToCookie();
			setAutoLogin();
			return SUCCESS;
		}

		addFieldError("password", "We're sorry, the email address or password you entered is incorrect. Please try again.");
		uiError += "password,";
		return INPUT;
	}

	private void setEmailToCookie() {
		cookieUtils.setCookie(CookieSpecification.EMAIL, email);
	}

	private void setAutoLogin() {
		cookieUtils.setCookie(CookieSpecification.AUTOLOGIN, rememberMe ? Constants.ON : Constants.OFF);
		if (rememberMe) {
			cookieUtils.setCookie(CookieSpecification.PASSWORD, password);
		} else {
			cookieUtils.removeCookie(CookieSpecification.PASSWORD);
		}
	}

	private void getEmailFromCookie() {
		String email = cookieUtils.getCookie(CookieSpecification.EMAIL);
		if (!Utils.isEmpty(email))
			setEmail(email);
	}

	private void getAutoLoginFromCookie() {
		if (Constants.ON.equals(cookieUtils.getCookie(CookieSpecification.AUTOLOGIN))) {
			rememberMe = true;
			email = cookieUtils.getCookie(CookieSpecification.EMAIL);
			password = cookieUtils.getCookie(CookieSpecification.PASSWORD);
		}
	}

	private boolean isUserLogined() {
		return !Utils.isEmpty(currUser);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getUiError() {
		return uiError;
	}
}