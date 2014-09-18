package com.acsys.common;

import com.acsys.core.base.action.BaseAction;

/**
 * @author Nealy
 * @date Sep 17, 2014
 */
public class PageNoFoundAction extends BaseAction {
	@Override
	public String execute() throws Exception {
		log.warn("PageNoFound");
		log.warn("requestURI:" + this.request.getRequestURI());
		return SUCCESS;
	}
}