package com.gitee.swsk33.readandwrite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gitee.swsk33.readandwrite.exception.RowsOutOfBoundsException;
import com.gitee.swsk33.readandwrite.param.CharSetValue;
import com.gitee.swsk33.readandwrite.param.NewLineCharacter;

/**
 * 文件写入器
 */
public class TextFileWriter {

	/**
	 * 异常校验，校验文件行数
	 *
	 * @param filePath  指定文件位置
	 * @param whichLine 指定行数
	 * @throws RowsOutOfBoundsException 行数不合法抛出异常
	 */
	private static void checkFileLine(String filePath, int whichLine) throws Exception {
		int line = LineScanner.getLineCount(filePath);
		if (whichLine <= 0) {
			throw new RowsOutOfBoundsException("指定行数不可小于等于0！");
		}
		if (whichLine > line) {
			throw new RowsOutOfBoundsException("指定行数不可超过文件固有行数！");
		}
	}

	/**
	 * 用指定内容替换文件指定行
	 *
	 * @param filePath  待写入文件相对/绝对路径
	 * @param whichLine 待替换的行数
	 * @param text      待替换的内容
	 * @return 替换写入操作成功即返回true
	 */
	public static boolean replaceLine(String filePath, int whichLine, String text) throws Exception {
		return replaceLine(filePath, whichLine, text, CharSetValue.defaultCharSet);
	}

	/**
	 * 用指定内容替换文件指定行，以指定的字符编码形式 <br>
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
	 * @param filePath  待写入文件相对/绝对路径
	 * @param whichLine 待替换的行数
	 * @param text      待替换的内容
	 * @param charSet   指定编码格式写入文件
	 * @return 替换写入操作成功即返回true
	 */
	public static boolean replaceLine(String filePath, int whichLine, String text, String charSet) throws Exception {
		checkFileLine(filePath, whichLine);
		String[] fileContent = TextFileReader.readFileToArray(filePath, charSet);
		fileContent[whichLine - 1] = text;
		return writeTextArray(filePath, fileContent, charSet);
	}

	/**
	 * 写入指定内容至文件末尾，每执行一次该方法就在末尾写入一行内容
	 *
	 * @param filePath 待写入文件相对/绝对路径
	 * @param text     待写入内容
	 * @return 写入操作成功即返回true
	 */
	public static boolean appendText(String filePath, String text) throws Exception {
		return appendText(filePath, text, CharSetValue.defaultCharSet);
	}

	/**
	 * 以指定的编码格式，写入指定内容至文件末尾，每执行一次该方法就在末尾写入一行内容 <br>
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
	 * @param filePath 待写入文件相对/绝对路径
	 * @param text     待写入内容
	 * @param charSet  指定编码格式写入文件
	 * @return 写入操作成功即返回true
	 */
	public static boolean appendText(String filePath, String text, String charSet) throws Exception {
		List<String> content = new ArrayList<>();
		Collections.addAll(content, TextFileReader.readFileToArray(filePath, charSet));
		content.add(text);
		return writeTextArray(filePath, content.toArray(new String[0]), charSet);
	}

	/**
	 * 将文本写入文件，文件原有内容会被覆盖
	 *
	 * @param filePath 被写入文件路径
	 * @param text     写入的内容
	 * @return 写入成功返回true
	 */
	public static boolean writeText(String filePath, String text) throws Exception {
		return writeText(filePath, text, CharSetValue.defaultCharSet);
	}

	/**
	 * 将文本以特定的编码写入到一个文件，文件原有内容会被覆盖<br>
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
	 * @param filePath 被写入文件路径
	 * @param text     写入的内容
	 * @param charSet  以特定编码写入
	 * @return 写入成功返回true
	 */
	public static boolean writeText(String filePath, String text, String charSet) throws Exception {
		File file = new File(filePath);
		FileOutputStream outputStream = new FileOutputStream(file);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charSet);
		BufferedWriter writer = new BufferedWriter(outputStreamWriter);
		writer.write(text);
		writer.close();
		outputStreamWriter.close();
		outputStream.close();
		// 最后检查是否写入成功
		return TextFileReader.readFile(filePath, charSet).equals(text);
	}

	/**
	 * 把一个字符串数组以一个特定的编码写入文件，文件原有内容会被覆盖
	 *
	 * @param filePath  待写入文件
	 * @param textArray 待写入字符串数组
	 * @return 写入成功返回true
	 */
	public static boolean writeTextArray(String filePath, String[] textArray) throws Exception {
		return writeTextArray(filePath, textArray, CharSetValue.defaultCharSet);
	}

	/**
	 * 把一个字符串数组以一个特定的编码写入文件，文件原有内容会被覆盖<br>
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
	 * @param filePath  待写入文件
	 * @param textArray 待写入字符串数组
	 * @param charSet   指定编码
	 * @return 写入成功返回true
	 */
	public static boolean writeTextArray(String filePath, String[] textArray, String charSet) throws Exception {
		return writeText(filePath, String.join(NewLineCharacter.defaultNewLineChar, textArray), charSet);
	}

	/**
	 * 移除某一行的内容
	 *
	 * @param filePath  文件路径
	 * @param whichLine 待移除的行
	 * @return 移除成功返回true
	 */
	public static boolean removeLine(String filePath, int whichLine) throws Exception {
		return removeLine(filePath, whichLine, CharSetValue.defaultCharSet);
	}

	/**
	 * 移除某一行的内容，并指定文件编码
	 *
	 * @param filePath  文件路径
	 * @param whichLine 待移除的行
	 * @param charSet   原始文件编码
	 * @return 移除成功返回true
	 */
	public static boolean removeLine(String filePath, int whichLine, String charSet) throws Exception {
		List<String> fileContents = new ArrayList<>();
		Collections.addAll(fileContents, TextFileReader.readFileToArray(filePath, charSet));
		fileContents.remove(whichLine - 1);
		return writeTextArray(filePath, fileContents.toArray(new String[0]), charSet);
	}

	/**
	 * 把指定文件内容清空
	 *
	 * @param filePath 待清空文件相对/绝对路径
	 * @return boolean 清空操作成功时返回true
	 */
	public static boolean clearAll(String filePath) throws Exception {
		File file = new File(filePath);
		FileOutputStream outputStream = new FileOutputStream(file);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		BufferedWriter writer = new BufferedWriter(outputStreamWriter);
		writer.write("");
		writer.close();
		outputStreamWriter.close();
		outputStream.close();
		// 检查是否操作成功
		return LineScanner.getLineCount(filePath) == 0;
	}

}