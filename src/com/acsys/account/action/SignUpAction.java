package com.acsys.account.action;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.acsys.account.model.User;
import com.acsys.account.service.IUserService;
import com.acsys.common.Constants;
import com.acsys.common.Utils;
import com.acsys.core.BaseAction;
import com.acsys.core.CommonContext;
import com.acsys.grouping.model.Grouping;
import com.acsys.grouping.service.IGroupingService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Nealy
 * @date Aug 6, 2014
 */
public class SignUpAction extends BaseAction implements Preparable {
	private static final long serialVersionUID = 7290224220873575164L;

	// private IUserService userService = (IUserService) SpringContext.getBean("userService");
	@Resource
	private IUserService userService;
	@Resource
	private IGroupingService groupingService;

	private String email;
	private String password;
	private String conPassword;
	private List<Grouping> groupings;
	private String groupingId;
	private String uiError;

	private User user;

	@Override
	public void prepare() throws Exception {
		groupings = groupingService.getAllGroupings();
		user = new User();
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
		if (Utils.isEmpty(conPassword)) {
			addFieldError("conPassword", "Please Confirm your password.");
			uiError += "conPassword,";
		}
		if (Utils.isEmpty(uiError)) {
			if (!isEmailCheck()) {
				addFieldError("email", "Please enter a valid email address. ");
				uiError += "email,";
			}
		}
		if (Utils.isEmpty(uiError)) {
			if (password.length() < 6 || password.length() > 20) {
				addFieldError("password", "Invalid password length. Please enter a password that is more than 6 characters and less than 20 characters.");
				uiError += "password,";
			}
		}
		if (Utils.isEmpty(uiError)) {
			if (email.equals(password)) {
				addFieldError("password", "You have entered your email as your password. Please enter a new password that is not your email address.");
			}
		}
		if (Utils.isEmpty(uiError)) {
			if (!(password.trim()).equals(conPassword.trim())) {
				addFieldError("conEmail", "Your first and second password do not match.");
				uiError += "conPassword,";
			}
		}
		if (Utils.isEmpty(uiError)) {
			User user = userService.getUserByEmail(email);
			if (!Utils.isEmpty(user)) {
				addFieldError("email", "This email address already exists in our system. If you are a returning customer, please click on the 'Sign In' link below.");
				uiError += "email,";
			}
		}
	}

	@Override
	public String input() {
		return INPUT;
	}

	public String submit() throws Exception {
		String id = userService.addUser(email, password, groupingId);

		// if (Utils.isEmpty(id)) {
		// addFieldError("email", "Sign up failed, Please try again.");
		// return INPUT;
		// }

		user = userService.getUserById(id);
		ActionContext.getContext().getSession().put(Constants.USER_SESSION, user);
		CommonContext.setCurrentUser(user);
		return SUCCESS;
	}

	private boolean isEmailCheck() {
		String reg = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
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

	public void setConPassword(String conPassword) {
		this.conPassword = conPassword;
	}

	public List<Grouping> getGroupings() {
		return groupings;
	}

	public void setGroupingId(String groupingId) {
		this.groupingId = groupingId;
	}

	public String getUiError() {
		return uiError;
	}
}