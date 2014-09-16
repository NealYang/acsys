package com.acsys.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Nealy
 * @date Sep 4, 2014
 */
public final class Log {
	private static Logger logger = LogManager.getLogger(Log.class.getName());

	public static void log(Level level, String message) {
		logger.log(level, message);
	}
}