package com.gitee.swsk33.readandwrite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * 用于获取或者释放jar包内的文件和资源
 */
public class JarUtils {

	/**
	 * 释放jar包内文件到jar包外部
	 *
	 * @param c          Class 指定的类（一般是当前类，非静态方法填入当前内用this.getClass()即可）
	 * @param classPath  指定被释放的资源文件路径，可以是指定的类（c）为基准的相对路径，也可以是绝对路径。绝对路径以/开头，表示jar包的根目录
	 * @param outputPath 指定的输出路径
	 * @return 释放成功则为true
	 */
	public static boolean releaseFileInJar(Class<?> c, String classPath, String outputPath) throws Exception {
		boolean success = false;
		File file = new File(outputPath);
		if (!file.getParentFile().exists()) {// 如果指定输出文件父目录不存在，创建目录
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {// 如果指定输出文件不存在，创建文件
			file.createNewFile();
		}
		int index = 0; // 当前读取的位数
		byte[] bytes = new byte[2048]; // 一次读取2048位
		InputStream inputStream = c.getResourceAsStream(classPath);
		OutputStream fileOutputStream = new FileOutputStream(file);
		while ((index = inputStream.read(bytes)) != -1) {
			fileOutputStream.write(bytes, 0, index);
		}
		fileOutputStream.flush();
		fileOutputStream.close();
		inputStream.close();
		return new File(outputPath).exists();
	}

	/**
	 * 直接读取jar内图片资源为ImageIcon对象
	 *
	 * @param c         Class 指定的类（一般是当前类，非静态方法填入当前内用this.getClass()即可）
	 * @param classPath 资源文件路径，可以是指定的类（c）为基准的相对路径，也可以是绝对路径。绝对路径以/开头，表示jar包的根目录
	 * @return 获取的包内图片对象
	 */
	public static ImageIcon getImageInJar(Class<?> c, String classPath) {
		URL imgurl = c.getResource(classPath);
		return new ImageIcon(imgurl);
	}

}