package com.gitee.swsk33.readandwrite;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

import com.gitee.swsk33.readandwrite.exception.SizeOutOfBoundsException;

/**
 * 二进制文件工具
 */
public class BinaryUtils {

	/**
	 * 读取二进制文件并储存在byte数组当中
	 *
	 * @param filePath 要读取的文件
	 * @return 读取到的文件字节内容
	 */
	public static byte[] readBinaryFile(String filePath) throws Exception {
		File f = new File(filePath);
		long size = f.length();
		byte[] content;
		if (size > Integer.MAX_VALUE) {
			throw new SizeOutOfBoundsException("待读取文件大小超出了可读取范围！请使用分段读取的方法读取！");
		}
		InputStream inputStream = new FileInputStream(f);
		content = new byte[(int) size];
		inputStream.read(content);
		inputStream.close();
		return content;
	}

	/**
	 * 写入二进制文件，文件夹不存在自动创建
	 *
	 * @param filePath 待写入的文件
	 * @param data     待写入的数据
	 * @return 是否写入成功
	 */
	public static boolean writeBinaryFile(String filePath, byte[] data) throws Exception {
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		OutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(data);
		fileOutputStream.close();
		return Arrays.equals(BinaryUtils.readBinaryFile(filePath), data);
	}

	/**
	 * 将对象（实例）序列化并写入文件<br>
	 * 被写入的对象必须是可序列化的（即被写入实例的类需要implements Serializable）
	 *
	 * @param <T>      实例类型
	 * @param filePath 写入文件路径
	 * @param object   待写入实例内容
	 * @return 是否写入成功
	 */
	public static <T> boolean writeObjectToFile(String filePath, T object) throws Exception {
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
		objectOutput.writeObject(object);
		byteOutput.close();
		objectOutput.close();
		return writeBinaryFile(filePath, byteOutput.toByteArray());
	}

	/**
	 * 从文件中读取数据并反序列化为对象<br>
	 * 读取的对象必须是可序列化的（即读取到的实例的类需要implements Serializable）
	 *
	 * @param <T>      待读取文件储存的数据对象类型
	 * @param filePath 待读取文件
	 * @return 读取到的结果
	 */
	public static <T> T readObjectFromFile(String filePath) throws Exception {
		byte[] readData = readBinaryFile(filePath);
		ByteArrayInputStream byteInput = new ByteArrayInputStream(readData);
		ObjectInputStream objectInput = new ObjectInputStream(byteInput);
		byteInput.close();
		objectInput.close();
		return (T) objectInput.readObject();
	}

	/**
	 * 复制文件，把文件从原文件路径复制到目标文件路径，目标文件路径目录不存在会自动创建
	 *
	 * @param origin      原文件路径
	 * @param destination 目标文件路径
	 * @return 是否复制成功
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
		InputStream fileInputStream = new FileInputStream(originFile);
		OutputStream fileOutputStream = new FileOutputStream(destFile);
		int index;
		byte[] fileByte = new byte[4096];
		while ((index = fileInputStream.read(fileByte)) != -1) {
			fileOutputStream.write(fileByte, 0, index);
		}
		fileInputStream.close();
		fileOutputStream.close();
		return FileComparer.compareBinaryFile(origin, destination);
	}

}