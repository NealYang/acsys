package com.acsys.common;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author Nealy
 * @date Jul 25, 2014
 */
public class Utils {
	public static boolean isEmpty(String string) {
		return string == null || "".equals(string);
	}

	public static boolean isEmpty(Object object) {
		return object == null;
	}

	public static String MD5(String string) {
		if (Utils.isEmpty(string))
			return "";

		MessageDigest md5 = null;
		String pwd = "";

		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(string.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] md5ByteArray = md5.digest();
		pwd = new BigInteger(1, md5ByteArray).toString(16);

		return pwd;
	}

	public static String toMySQLString(String[] strings) {
		String ret = "";
		if (strings == null || strings.length < 1)
			return ret;
		for (String string : strings) {
			if (isEmpty(ret)) {
				ret += string;
			} else {
				ret += "," + string;
			}
		}
		return ret;
	}
}