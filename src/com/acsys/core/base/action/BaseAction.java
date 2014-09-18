package com.acsys.core.base.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Nealy
 * @date Jul 25, 2014
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements ServletRequestAware {
	protected HttpServletRequest request;
	public final Logger log = LogManager.getLogger(getClass().getName());

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}