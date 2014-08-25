package com.acsys.core;

import java.util.Date;

import com.acsys.account.model.User;

/**
 * @author Nealy
 * @date Jul 29, 2014
 */
public class CommonContext {
	private static User currentUser = null;
	private static Date setupDate = new Date();

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		CommonContext.currentUser = currentUser;
	}

	public static Date getSetupDate() {
		return setupDate;
	}

	public static Date getCurrentDate() {
		return new Date();
	}
}