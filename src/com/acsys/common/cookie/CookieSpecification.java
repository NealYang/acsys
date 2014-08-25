package com.acsys.common.cookie;

import javax.servlet.http.Cookie;

/**
 * @author Nealy
 * @date Aug 13, 2014
 */
public enum CookieSpecification {
	EMAIL("email", "/", CookieUtils.TEN_YEARS), PASSWORD("password", "/", CookieUtils.FOURTY_FIVE_DAYS), AUTOLOGIN("autoLogin", "/", CookieUtils.FIVE_DAYS);

	private String cookieName;
	private String path;
	private int maxAge;

	private CookieSpecification(String cookieName, String path, int maxAge) {
		this.cookieName = cookieName;
		this.path = path;
		this.maxAge = maxAge;
	}

	public Cookie createCookie(String value) {
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);
		return cookie;
	}

	public String getCookieName() {
		return cookieName;
	}
}