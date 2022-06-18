package com.gitee.swsk33.readandwrite.param;

/**
 * 换行符常量
 */
public class NewLineCharacter {

	public static String defaultNewLineChar = "\r\n";

	static {
		if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
			defaultNewLineChar = "\n";
		}
	}

	/**
	 * Windows换行符
	 */
	public static final String WINDOWS = "\r\n";

	/**
	 * Linux或者Mac OS换行符
	 */
	public static final String UNIX = "\n";

}