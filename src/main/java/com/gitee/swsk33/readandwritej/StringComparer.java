package com.gitee.swsk33.readandwritej;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * 文件文本对比器
 * 
 * @author swsk33
 *
 */
public class StringComparer {

	/**
	 * 比较某一行内容是否与被比较字符串一致
	 * 
	 * @param filepath     待比较的文件相对路径/绝对路径
	 * @param lineCompared 被比较的字符串
	 * @return boolean 布尔值 表示是否有一行的内容和被比较字符串一致
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public boolean compareLine(String filepath, String lineCompared) throws Exception {
		boolean result = false;
		int line = new LineScanner().getLineCount(filepath);
		File f = new File(filepath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < line; i++) {
			if (br.readLine().equals(lineCompared)) {
				result = true;
				break;
			}
		}
		br.close();
		return result;
	}

	/**
	 * 判断文本文档内是否包含被比较的字符串
	 * 
	 * @param filepath     待比较的文件相对路径/绝对路径
	 * @param textCompared 被比较的字符串
	 * @return boolean 布尔值 表示是否有一行的内容包含被比较的字符串
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public boolean compareText(String filepath, String textCompared) throws Exception {
		boolean result = false;
		int line = new LineScanner().getLineCount(filepath);
		File f = new File(filepath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < line; i++) {
			if (br.readLine().contains(textCompared)) {
				result = true;
				break;
			}
		}
		br.close();
		return result;
	}

}