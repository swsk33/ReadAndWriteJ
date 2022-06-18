package com.gitee.swsk33.readandwrite;

import java.util.Arrays;

/**
 * 文件对比：对比两个文件内容是否一致
 */
public class FileComparer {

	/**
	 * 比较两个文本文件的内容是否完全相同
	 *
	 * @param filePath1 待比较的文件1
	 * @param filePath2 待比较的文件2
	 * @return 表示两个文件是否一样
	 */
	public static boolean compareTextFile(String filePath1, String filePath2) throws Exception {
		return TextFileReader.readFile(filePath1).equals(TextFileReader.readFile(filePath2));
	}

	/**
	 * 比较两个二进制文件的内容是否完全相同
	 *
	 * @param filePath1 待比较的文件1
	 * @param filePath2 待比较的文件2
	 * @return 表示两个文件是否一样
	 */
	public static boolean compareBinaryFile(String filePath1, String filePath2) throws Exception {
		byte[] fileBytes1 = BinaryUtils.readBinaryFile(filePath1);
		byte[] fileBytes2 = BinaryUtils.readBinaryFile(filePath2);
		return Arrays.equals(fileBytes1, fileBytes2);
	}

}