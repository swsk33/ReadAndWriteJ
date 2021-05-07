package com.gitee.swsk33.readandwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.BufferedReader;

/**
 * 文件对比：对比两个文件内容是否一致
 * 
 * @author swsk33
 *
 */
public class FileComparer {

	/**
	 * 比较两个文本文件的内容是否完全相同
	 * 
	 * @param filePath1 待比较的文件1
	 * @param filePath2 待比较的文件2
	 * @return boolean 布尔值 表示两个文件是否一样
	 * @throws Exception 其中有一个文件不存在或者文件存在错误时抛出异常
	 */
	public static boolean compareTextFile(String filePath1, String filePath2) throws Exception {
		boolean result = false;
		File f1 = new File(filePath1); // 读取第一个文件
		int fl1 = LineScanner.getLineCount(filePath1);
		String s1 = "";
		FileInputStream fis1 = new FileInputStream(f1);
		InputStreamReader isr1 = new InputStreamReader(fis1);
		BufferedReader br1 = new BufferedReader(isr1);
		for (int i1 = 0; i1 < fl1; i1++) {
			s1 = s1 + br1.readLine() + "\r\n";
		}
		br1.close();
		File f2 = new File(filePath2); // 读取第二个文件
		int fl2 = LineScanner.getLineCount(filePath2);
		String s2 = "";
		FileInputStream fis2 = new FileInputStream(f2);
		InputStreamReader isr2 = new InputStreamReader(fis2);
		BufferedReader br2 = new BufferedReader(isr2);
		for (int i2 = 0; i2 < fl2; i2++) {
			s2 = s2 + br2.readLine() + "\r\n";
		}
		br2.close();
		if (s1.equals(s2)) {
			result = true;
		}
		return result;
	}

	/**
	 * 比较两个二进制文件的内容是否完全相同
	 * 
	 * @param filePath1 待比较的文件1
	 * @param filePath2 待比较的文件2
	 * @return boolean 布尔值 表示两个文件是否一样
	 * @throws Exception 其中有一个文件不存在或者文件存在错误时抛出异常
	 */
	public static boolean compareBinaryFile(String filePath1, String filePath2) throws Exception {
		byte[] fc1 = BinaryUtil.readBinaryFile(filePath1);
		byte[] fc2 = BinaryUtil.readBinaryFile(filePath2);
		return Arrays.equals(fc1, fc2);
	}

}