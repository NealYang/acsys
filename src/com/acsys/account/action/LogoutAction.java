package com.acsys.account.action;

import javax.annotation.Resource;

import com.acsys.common.Constants;
import com.acsys.common.cookie.CookieSpecification;
import com.acsys.common.cookie.CookieUtils;
import com.acsys.core.BaseAction;
import com.acsys.core.CommonContext;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Nealy
 * @date Jul 29, 2014
 */
public class LogoutAction extends BaseAction {
	@Resource
	private CookieUtils cookieUtils;

	@Override
	public String execute() {
		resetAutoLogin();
		ActionContext.getContext().getSession().put(Constants.USER_SESSION, null);
		CommonContext.setCurrentUser(null);
		return INPUT;
	}

	private void resetAutoLogin() {
		cookieUtils.removeCookie(CookieSpecification.AUTOLOGIN);
		cookieUtils.removeCookie(CookieSpecification.PASSWORD);
	}
}