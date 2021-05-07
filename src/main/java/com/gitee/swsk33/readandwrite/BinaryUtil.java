package com.gitee.swsk33.readandwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import com.gitee.swsk33.readandwrite.exception.SizeOutOfBoundsException;

/**
 * 二进制文件工具
 * 
 * @author swsk33
 *
 */
public class BinaryUtil {

	/**
	 * 读取二进制文件并储存在byte数组当中
	 * 
	 * @param filePath 要读取的文件
	 * @return byte[] 读取到的文件字节内容
	 * @throws Exception 文件不存在抛出异常
	 */
	public static byte[] readBinaryFile(String filePath) throws Exception {
		File f = new File(filePath);
		long size = f.length();
		byte[] content;
		if (size > Integer.MAX_VALUE) {
			throw new SizeOutOfBoundsException("待读取文件大小超出了可读取范围！请使用分段读取的方法读取！");
		} else {
			InputStream is = new FileInputStream(f);
			content = new byte[(int) size];
			is.read(content);
			is.close();
		}
		return content;
	}

	/**
	 * 分段读取二进制文件并储存在byte二维数组中
	 * 
	 * @param filePath  要读取的文件
	 * @param fragments 分段数
	 * @return byte[i][j] 读取到的文件字节内容，为二维数组，下标1代表第i+1个片段，下标2代表第i+1个片段的第j+1个字节
	 * @throws Exception 文件不存在时抛出异常
	 */
	public static byte[][] readBinaryFile(String filePath, int fragments) throws Exception {
		File f = new File(filePath);
		byte[][] content = new byte[fragments][];
		long size = f.length();
		if (fragments > size) {
			throw new SizeOutOfBoundsException("指定分段数不可以大于文件大小！");
		} else {
			long eachSize = size / fragments;
			long lastSize = size % fragments;
			InputStream is = new FileInputStream(f);
			if (lastSize == 0) {
				for (int i = 0; i < fragments; i++) {
					byte[] temp = new byte[(int) eachSize];
					is.read(temp);
					content[i] = temp;
				}
			} else {
				for (int i = 0; i < fragments - 1; i++) {
					byte[] temp = new byte[(int) eachSize];
					is.read(temp);
					content[i] = temp;
				}
				byte[] last = new byte[(int) lastSize];
				is.read(last);
				content[fragments - 1] = last;
			}
			is.close();
		}
		return content;
	}

	/**
	 * 写入二进制文件（小文件）
	 * 
	 * @param filePath 待写入的文件
	 * @param data     待写入的数据
	 * @return boolean 是否写入成功
	 * @throws Exception 文件错误抛出异常
	 */
	public static boolean writeBinaryFile(String filePath, byte[] data) throws Exception {
		boolean success = false;
		File f = new File(filePath);
		File fp = new File(f.getParent());
		if (!fp.exists()) {
			fp.mkdirs();
		}
		if (!f.exists()) {
			f.createNewFile();
		}
		OutputStream os = new FileOutputStream(f);
		os.write(data);
		os.close();
		if (Arrays.equals(BinaryUtil.readBinaryFile(filePath), data)) {
			success = true;
		}
		return success;
	}

	/**
	 * 复制文件，把文件从原文件路径复制到目标文件路径，目标文件路径目录不存在会自动创建
	 * 
	 * @param origin      原文件路径
	 * @param destination 目标文件路径
	 * @return boolean 是否复制成功
	 * @throws Exception 原文件不存在时抛出异常
	 */
	public static boolean copyFile(String origin, String destination) throws Exception {
		File originFile = new File(origin);
		File destFile = new File(destination);
		File parentDir = new File(destFile.getParent());
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		InputStream is = new FileInputStream(originFile);
		OutputStream os = new FileOutputStream(destFile);
		int index;
		byte[] fileByte = new byte[4096];
		while ((index = is.read(fileByte)) != -1) {
			os.write(fileByte, 0, index);
		}
		is.close();
		os.close();
		return FileComparer.compareBinaryFile(origin, destination);
	}

}