package com.gitee.swsk33.readandwrite;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 文件分析器，用于读取文件大小和格式（扩展名）
 */
public class FileAnalyzer {

	/**
	 * 用于获取文件的MD5值
	 *
	 * @param filePath 文件的相对路径/绝对路径
	 * @return 文件MD5值
	 */
	public static String getFileMD5(String filePath) {
		String result = "";
		BigInteger bigInteger = null;
		try {
			byte[] buffer = new byte[8192];
			int len = 0;
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			File file = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(file);
			while ((len = fileInputStream.read(buffer)) != -1) {
				md5.update(buffer, 0, len);
			}
			fileInputStream.close();
			byte[] digest = md5.digest();
			bigInteger = new BigInteger(1, digest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = bigInteger.toString(16);
		return result;
	}

	/**
	 * 获取文件的大小，单位为字节(Byte)
	 *
	 * @param filePath 文件的相对路径/绝对路径
	 * @return 文件大小
	 */
	public static long getFileSizeByte(String filePath) {
		return new File(filePath).length();
	}

	/**
	 * 获取文件的大小，单位为千字节(KB)
	 *
	 * @param filePath 文件的相对路径/绝对路径
	 * @return 文件大小
	 */
	public static double getFileSizeKb(String filePath) {
		return (double) getFileSizeByte(filePath) / 1024;
	}

	/**
	 * 获取文件的大小，单位为MB
	 *
	 * @param filePath 文件的相对路径/绝对路径
	 * @return 文件大小
	 */
	public static double getFileSizeMb(String filePath) {
		return getFileSizeKb(filePath) / 1024;
	}

	/**
	 * 获取文件的大小，单位为GB
	 *
	 * @param filePath 文件的相对路径/绝对路径
	 * @return double 浮点型 文件大小
	 */
	public static double getFileSizeGb(String filePath) {
		return getFileSizeMb(filePath) / 1024;
	}

	/**
	 * 获取文件格式（扩展名）
	 *
	 * @param filePath 文件的相对路径/绝对路径
	 * @return 为获取文件扩展名（不带.），没有扩展名返回null
	 */
	public static String getFileFormat(String filePath) {
		String name = new File(filePath).getName();
		if (name.contains(".")) {
			return name.substring(name.lastIndexOf(".") + 1);
		}
		return null;
	}

}