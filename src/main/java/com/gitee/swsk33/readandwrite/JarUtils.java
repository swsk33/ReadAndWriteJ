package com.gitee.swsk33.readandwrite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * 用于获取或者释放jar包内的文件和资源
 * 
 * @author swsk33
 *
 */
public class JarUtils {

	/**
	 * 释放jar包内文件到jar包外部
	 * 
	 * @param c          Class 指定的类（一般是当前类，非静态方法填入当前内用this.getClass()即可）
	 * @param classPath  指定被释放的资源文件路径，可以是指定的类（c）为基准的相对路径，也可以是绝对路径。绝对路径以/开头，表示jar包的根目录
	 * @param outputPath 指定的输出路径
	 * @return boolean 释放成功则为true
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public static boolean releaseFileInJar(@SuppressWarnings("rawtypes") Class c, String classPath, String outputPath) throws Exception {
		boolean success = false;
		InputStream is = c.getResourceAsStream(classPath);
		File f = new File(outputPath);
		File fp = new File(f.getParent());
		if (!fp.exists()) {// 如果指定输出文件父目录不存在，创建目录
			fp.mkdirs();
		}
		if (!f.exists()) {// 如果指定输出文件不存在，创建文件
			f.createNewFile();
		}
		OutputStream os = new FileOutputStream(f);
		int index = 0; // 当前读取的位数
		byte[] bytes = new byte[2048]; // 一次读取2048位
		while ((index = is.read(bytes)) != -1) {
			os.write(bytes, 0, index);
		}
		os.flush();
		os.close();
		is.close();
		if (new File(outputPath).exists()) {
			success = true;
		}
		return success;
	}

	/**
	 * 直接读取jar内图片资源为ImageIcon对象
	 * 
	 * @param c         Class 指定的类（一般是当前类，非静态方法填入当前内用this.getClass()即可）
	 * @param classPath 资源文件路径，可以是指定的类（c）为基准的相对路径，也可以是绝对路径。绝对路径以/开头，表示jar包的根目录
	 * @return ImageIcon 获取的包内图片对象
	 */
	public static ImageIcon getImageInJar(@SuppressWarnings("rawtypes") Class c, String classPath) {
		URL imgurl = c.getResource(classPath);
		return new ImageIcon(imgurl);
	}

}