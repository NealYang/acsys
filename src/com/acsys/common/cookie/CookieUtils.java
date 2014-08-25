package com.acsys.common.cookie;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 * @author Nealy
 * @date Aug 13, 2014
 */
public class CookieUtils {
	private static final int DELETE_IMMEDIATELY = 0;
	public static final int DELETE_WHEN_BROWSER_EXITS = -1;
	public static final int FOURTY_FIVE_DAYS = 45 * 24 * 3600;
	public static final int FIVE_DAYS = 5 * 24 * 3600;
	public static final int TEN_YEARS = 315360000;

	public void setCookie(CookieSpecification cookieSpecification, String value) {
		if (value == null)
			return;
		String encodedValue = URLEncoder.encode(value);
		Cookie cookie = cookieSpecification.createCookie(encodedValue);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addCookie(cookie);
	}

	public String getCookie(CookieSpecification cookieSpecification) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if (cookieSpecification.getCookieName().equals(cookieName)) {
				String value = cookie.getValue();
				if (value == null)
					return value;
				return URLDecoder.decode(value);
			}
		}
		return null;
	}

	public void removeCookie(CookieSpecification cookieSpecification) {
		Cookie cookie = cookieSpecification.createCookie("");
		cookie.setMaxAge(DELETE_IMMEDIATELY);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addCookie(cookie);
	}
}