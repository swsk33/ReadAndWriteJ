package com.gitee.swsk33.readandwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * 文件行数读取器
 */
public class LineScanner {

	/**
	 * 获取文本文件的内容行数
	 *
	 * @param filePath 待读取文件的相对路径/绝对路径
	 * @return 文件行数
	 */
	public static int getLineCount(String filePath) throws Exception {
		int linaCount = 0;
		File file = new File(filePath);
		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		for (int i = 1; bufferedReader.readLine() != null; i++) {
			linaCount = i;
		}
		bufferedReader.close();
		return linaCount;
	}

	/**
	 * 判断一段文本在文件的哪一行
	 *
	 * @param filePath 文件位置
	 * @param text     待判断内容
	 * @return 待判断内容位于文件中的行数，若指定内容在文件中不存在返回-1
	 */
	public static int getTextAtLine(String filePath, String text) throws Exception {
		String[] fileContent = TextFileReader.readFileToArray(filePath);
		for (int i = 0; i < fileContent.length; i++) {
			if (fileContent[i].equals(text)) {
				return i + 1;
			}
		}
		return -1;
	}

}