package com.gitee.swsk33.readandwrite.param;

import java.util.Locale;

/**
 * 读写字符编码值常量
 */
public class CharSetValue {

	public static String defaultCharSet;

	static {
		Locale locale = Locale.getDefault();
		if (locale.getLanguage().equals("zh")) {
			defaultCharSet = "GBK";
		} else {
			defaultCharSet = "UTF-8";
		}
	}

	/**
	 * 读写编码：US-ASCII
	 */
	public static final String US_ASCII = "US-ASCII";

	/**
	 * 读写编码：ISO-8859-1
	 */
	public static final String ISO_8859_1 = "ISO-8859-1";

	/**
	 * 读写编码：GBK
	 */
	public static final String GBK = "GBK";

	/**
	 * 读写编码：UTF-8
	 */
	public static final String UTF_8 = "UTF-8";

	/**
	 * 读写编码：UTF-16
	 */
	public static final String UTF_16 = "UTF-16";

	/**
	 * 读写编码：UTF-16BE
	 */
	public static final String UTF_16BE = "UTF-16BE";

	/**
	 * 读写编码：UTF-16LE
	 */
	public static final String UTF_16LE = "UTF-16LE";

}