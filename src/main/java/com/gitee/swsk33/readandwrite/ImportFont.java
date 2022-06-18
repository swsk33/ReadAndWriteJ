package com.gitee.swsk33.readandwrite;

import java.awt.Font;
import java.io.File;

/**
 * 引入字体：在GUI编程中可以快速指定字体文件以设置字体
 */
public class ImportFont {

	/**
	 * 引入字体文件为普通字体形式
	 *
	 * @param filePath 字体文件的相对路径/绝对路径
	 * @param wordSize 字体大小
	 * @return 字体对象
	 */
	public static Font getFont(String filePath, int wordSize) {
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(filePath));
			font = font.deriveFont(Font.PLAIN, wordSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return font;
	}

	/**
	 * 引入字体文件为加粗字体形式
	 *
	 * @param filePath 字体文件的相对路径/绝对路径
	 * @param wordSize 字体大小
	 * @return 字体对象
	 */
	public static Font getBoldFont(String filePath, int wordSize) {
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(filePath));
			font = font.deriveFont(Font.BOLD, wordSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return font;
	}

	/**
	 * 引入字体文件为斜体字体形式
	 *
	 * @param filePath 字体文件的相对路径/绝对路径
	 * @param wordSize 字体大小
	 * @return 字体对象
	 */
	public static Font getItalicFont(String filePath, int wordSize) {
		Font font;
		File file = new File(filePath);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, file);
			font = font.deriveFont(Font.ITALIC, wordSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return font;
	}

	/**
	 * 引入字体文件为斜体加粗字体形式
	 *
	 * @param filePath 字体文件的相对路径/绝对路径
	 * @param wordSize 字体大小
	 * @return 字体对象
	 */
	public static Font getItalicBoldFont(String filePath, int wordSize) {
		Font font;
		File file = new File(filePath);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, file);
			font = font.deriveFont(Font.ITALIC | Font.BOLD, wordSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return font;
	}

}