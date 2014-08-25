package com.acsys.struts.interceptor;

import java.util.Map;

import com.acsys.account.model.User;
import com.acsys.common.Constants;
import com.acsys.common.Utils;
import com.acsys.core.CommonContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author Nealy
 * @date Jul 25, 2014
 */
public class LoginInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		Map session = actionContext.getSession();
		User user = (User) session.get(Constants.USER_SESSION);

		if (!Utils.isEmpty(user)) {
			CommonContext.setCurrentUser(user);
			return invocation.invoke();
		}

		CommonContext.setCurrentUser(null);
		actionContext.put("loginWarn", "You must first login to continue ^_^");
		return Action.LOGIN;
	}
}