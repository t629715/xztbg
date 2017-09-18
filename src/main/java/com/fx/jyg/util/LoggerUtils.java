package com.fx.jyg.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* @ClassName: LoggerUtils 
* @Description: 日志工具类
* @author jcwang
* @date 2017年7月31日 下午3:59:57 
*
 */
public class LoggerUtils {

	private static String NAME = LoggerUtils.class.getName();

	private LoggerUtils() {
	}

	public static Logger getLogger() {
		return getLogger(getClassName());
	}

	private static String getClassName() {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		for (int i = 1; i < stacks.length; i++) {
			if (!NAME.equals(stacks[i].getClassName())){
				return stacks[i].getClassName();
			}
		}
		return NAME;
	}

	public static Logger getLogger(String className) {
		return LoggerFactory.getLogger(className);
	}
}
