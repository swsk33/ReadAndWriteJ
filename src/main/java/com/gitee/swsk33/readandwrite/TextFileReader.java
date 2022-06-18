package com.gitee.swsk33.readandwrite;

import com.gitee.swsk33.readandwrite.exception.RowsOutOfBoundsException;
import com.gitee.swsk33.readandwrite.param.CharSetValue;
import com.gitee.swsk33.readandwrite.param.NewLineCharacter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 文件读取器
 */
public class TextFileReader {

	/**
	 * 用于检测文件行数与传入起始/终止的行数是否合法，不合法将抛出异常
	 *
	 * @param filePath 待检测文件
	 * @param start    起始行
	 * @param end      终止行
	 * @throws RowsOutOfBoundsException 行数不合法异常
	 */
	private static void checkFileLine(String filePath, int start, int end) throws Exception {
		int fileLine = LineScanner.getLineCount(filePath);
		if (start > end) {
			throw new RowsOutOfBoundsException("终止行数不能小于起始行数！");
		}
		if (start <= 0) {
			throw new RowsOutOfBoundsException("起始行数不能小于等于0！");
		}
		if (start > fileLine) {
			throw new RowsOutOfBoundsException("起始行数不能大于文档的总行数！");
		}
		if (end > fileLine) {
			throw new RowsOutOfBoundsException("终止行数不能大于文档的总行数！");
		}
	}

	/**
	 * 读取文本文件指定行内容
	 *
	 * @param filePath 待读取文件相对/绝对路径
	 * @param line     待读取的行数
	 * @return 读取的内容
	 */
	public static String readLine(String filePath, int line) throws Exception {
		return readLine(filePath, line, CharSetValue.defaultCharSet);
	}

	/**
	 * 指定编码读取文本文件指定行内容<br>
	 * 可用的编码常量值有：
	 * <ul>
	 * <li>CharSetValue.US_ASCII：<strong>US-ASCII</strong></li>
	 * <li>CharSetValue.ISO_8859_1：<strong>ISO-8859-1</strong></li>
	 * <li>CharSetValue.GBK：<strong>GBK</strong></li>
	 * <li>CharSetValue.UTF_8：<strong>UTF-8</strong></li>
	 * <li>CharSetValue.UTF_16：<strong>UTF-16</strong></li>
	 * <li>CharSetValue.UTF_16BE：<strong>UTF-16BE</strong></li>
	 * <li>CharSetValue.UTF_16LE：<strong>UTF-16LE</strong></li>
	 * </ul>
	 * CharSetValue类在com.gitee.swsk33.readandwrite.param下<br>
	 * <br>
	 *
	 * @param filePath 待读取文件相对/绝对路径
	 * @param line     待读取的行数
	 * @param charSet  指定编码格式读取文件
	 * @return 读取的内容
	 */
	public static String readLine(String filePath, int line, String charSet) throws Exception {
		String result = null;
		File file = new File(filePath);
		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charSet);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		for (int i = 0; i < line; i++) {
			result = bufferedReader.readLine();
		}
		bufferedReader.close();
		return result;
	}

	/**
	 * 读取指定行数范围内的内容并以字符串形式储存
	 *
	 * @param filePath 待读取文件相对/绝对路径
	 * @param start    指定起始行
	 * @param end      指定终止行
	 * @return 读取的内容
	 */
	public static String readFileRange(String filePath, int start, int end) throws Exception {
		return readFileRange(filePath, start, end, CharSetValue.defaultCharSet);
	}

	/**
	 * 以指定编码读取指定行数范围内的内容并以字符串形式储存<br>
	 * 可用的编码常量值有：
	 * <ul>
	 * <li>CharSetValue.US_ASCII：<strong>US-ASCII</strong></li>
	 * <li>CharSetValue.ISO_8859_1：<strong>ISO-8859-1</strong></li>
	 * <li>CharSetValue.GBK：<strong>GBK</strong></li>
	 * <li>CharSetValue.UTF_8：<strong>UTF-8</strong></li>
	 * <li>CharSetValue.UTF_16：<strong>UTF-16</strong></li>
	 * <li>CharSetValue.UTF_16BE：<strong>UTF-16BE</strong></li>
	 * <li>CharSetValue.UTF_16LE：<strong>UTF-16LE</strong></li>
	 * </ul>
	 * CharSetValue类在com.gitee.swsk33.readandwrite.param下<br>
	 * <br>
	 *
	 * @param filePath 待读取文件相对/绝对路径
	 * @param start    指定起始行
	 * @param end      指定终止行
	 * @param charSet  指定编码格式读取文件
	 * @return 读取的内容
	 */
	public static String readFileRange(String filePath, int start, int end, String charSet) throws Exception {
		checkFileLine(filePath, start, end);
		return String.join(NewLineCharacter.defaultNewLineChar, readFileRangeToArray(filePath, start, end, charSet));
	}

	/**
	 * 读取指定行数范围内的内容并以字符串数组形式储存，每一行的内容即为数组中的一个String元素
	 *
	 * @param filePath 待读取文件相对/绝对路径
	 * @param start    指定起始行
	 * @param end      指定终止行
	 * @return 读取的内容
	 */
	public static String[] readFileRangeToArray(String filePath, int start, int end) throws Exception {
		return readFileRangeToArray(filePath, start, end, CharSetValue.defaultCharSet);
	}

	/**
	 * 以指定编码读取指定行数范围内的内容并以字符串数组形式储存 每一行的内容即为数组中的一个String元素<br>
	 * 可用的编码常量值有：
	 * <ul>
	 * <li>CharSetValue.US_ASCII：<strong>US-ASCII</strong></li>
	 * <li>CharSetValue.ISO_8859_1：<strong>ISO-8859-1</strong></li>
	 * <li>CharSetValue.GBK：<strong>GBK</strong></li>
	 * <li>CharSetValue.UTF_8：<strong>UTF-8</strong></li>
	 * <li>CharSetValue.UTF_16：<strong>UTF-16</strong></li>
	 * <li>CharSetValue.UTF_16BE：<strong>UTF-16BE</strong></li>
	 * <li>CharSetValue.UTF_16LE：<strong>UTF-16LE</strong></li>
	 * </ul>
	 * CharSetValue类在com.gitee.swsk33.readandwrite.param下<br>
	 * <br>
	 *
	 * @param filePath 待读取文件相对/绝对路径
	 * @param start    指定起始行
	 * @param end      指定终止行
	 * @param charSet  指定编码格式读取文件
	 * @return 读取的内容
	 */
	public static String[] readFileRangeToArray(String filePath, int start, int end, String charSet) throws Exception {
		checkFileLine(filePath, start, end);
		return new ArrayList<>(Arrays.asList(readFileToArray(filePath, charSet)).subList(start - 1, end)).toArray(new String[0]);
	}

	/**
	 * 读取整个文本文档并将内容储存在字符串中
	 *
	 * @param filePath 待读取文件相对/绝对路径
	 * @return 读取的内容
	 */
	public static String readFile(String filePath) throws Exception {
		return readFile(filePath, CharSetValue.defaultCharSet);
	}

	/**
	 * 以指定编码读取整个文本文档并将内容储存在字符串中<br>
	 * 可用的编码常量值有：
	 * <ul>
	 * <li>CharSetValue.US_ASCII：<strong>US-ASCII</strong></li>
	 * <li>CharSetValue.ISO_8859_1：<strong>ISO-8859-1</strong></li>
	 * <li>CharSetValue.GBK：<strong>GBK</strong></li>
	 * <li>CharSetValue.UTF_8：<strong>UTF-8</strong></li>
	 * <li>CharSetValue.UTF_16：<strong>UTF-16</strong></li>
	 * <li>CharSetValue.UTF_16BE：<strong>UTF-16BE</strong></li>
	 * <li>CharSetValue.UTF_16LE：<strong>UTF-16LE</strong></li>
	 * </ul>
	 * CharSetValue类在com.gitee.swsk33.readandwrite.param下<br>
	 * <br>
	 *
	 * @param filePath 待读取文件相对/绝对路径
	 * @param charSet  指定编码格式读取文件
	 * @return 读取的内容
	 */
	public static String readFile(String filePath, String charSet) throws Exception {
		return String.join(NewLineCharacter.defaultNewLineChar, readFileToArray(filePath, charSet));
	}

	/**
	 * 读取整个文本文档并将内容储存在字符串数组中 每一行的内容都是String数组中的一个元素
	 *
	 * @param filePath 待读取文件相对/绝对路径
	 * @return 读取的内容
	 */
	public static String[] readFileToArray(String filePath) throws Exception {
		return readFileToArray(filePath, CharSetValue.defaultCharSet);
	}

	/**
	 * 以指定编码读取整个文本文档并将内容储存在字符串数组中 每一行的内容都是String数组中的一个元素<br>
	 * 可用的编码常量值有：
	 * <ul>
	 * <li>CharSetValue.US_ASCII：<strong>US-ASCII</strong></li>
	 * <li>CharSetValue.ISO_8859_1：<strong>ISO-8859-1</strong></li>
	 * <li>CharSetValue.GBK：<strong>GBK</strong></li>
	 * <li>CharSetValue.UTF_8：<strong>UTF-8</strong></li>
	 * <li>CharSetValue.UTF_16：<strong>UTF-16</strong></li>
	 * <li>CharSetValue.UTF_16BE：<strong>UTF-16BE</strong></li>
	 * <li>CharSetValue.UTF_16LE：<strong>UTF-16LE</strong></li>
	 * </ul>
	 * CharSetValue类在com.gitee.swsk33.readandwrite.param下<br>
	 * <br>
	 *
	 * @param filePath 待读取文件相对/绝对路径
	 * @param charSet  指定编码格式读取文件
	 * @return 读取的内容
	 */
	public static String[] readFileToArray(String filePath, String charSet) throws Exception {
		ArrayList<String> lines = new ArrayList<>();
		File file = new File(filePath);
		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charSet);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		int lineCount = LineScanner.getLineCount(filePath);
		for (int i = 0; i < lineCount; i++) {
			lines.add(bufferedReader.readLine());
		}
		bufferedReader.close();
		return lines.toArray(new String[0]);
	}

}