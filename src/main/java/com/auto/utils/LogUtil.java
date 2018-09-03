package com.auto.utils;

import org.apache.log4j.Logger;

public class LogUtil {
	private final Class<?> clazz;
	private Logger logger;

	public LogUtil(Class<?> clazz) {
		this.clazz = clazz;
		this.logger = Logger.getLogger(this.clazz);
	}

	public void info(String message) {
		logger.info(message);
	}

	public void debug(String message) {
		logger.debug(message);
	}

	public void error(String message) {
		logger.error(message);
	}

	public void trace(String message) {
		logger.trace(message);
	}
}
