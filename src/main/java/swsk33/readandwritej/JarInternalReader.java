package swsk33.readandwritej;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import javax.swing.ImageIcon;
import swsk33.readandwritej.exception.RowsOutOfBoundsException;

/**
 * 用于读取jar包内的文件和资源，支持读取文件，释放文件，读取为图片格式
 * 
 * @author swsk33
 *
 */
public class JarInternalReader {
	
	/**
	 * 读取jar内文本文件的行数
	 * 
	 * @param c         Class 指定的类（一般是当前类，非静态方法填入当前内用this.getClass()即可）
	 * @param classPath 资源文件路径，可以是指定的类（c）为基准的相对路径，也可以是绝对路径。绝对路径以/开头，表示jar包的根目录
	 * @return int 表示获取到的文件行数
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public int getFileLineCountInJar(Class c, String classPath) throws Exception {
		int line = 0;
		InputStream is = c.getResourceAsStream(classPath);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while (br.readLine() != null) {
			line++;
		}
		br.close();
		return line;
	}

	/**
	 * 读取jar内文本文件所有内容储存为String
	 * 
	 * @param c         Class 指定的类（一般是当前类，非静态方法填入当前内用this.getClass()即可）
	 * @param classPath 资源文件路径，可以是指定的类（c）为基准的相对路径，也可以是绝对路径。绝对路径以/开头，表示jar包的根目录
	 * @return String 表示读取的内容
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public String readFileInJar(Class c, String classPath) throws Exception {
		String result = "";
		String line = "";
		InputStream is = c.getResourceAsStream(classPath);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			result = result + line + "\r\n";
		}
		br.close();
		return result;
	}

	/**
	 * 读取jar内文本文件的指定行
	 * 
	 * @param c         Class 指定的类（一般是当前类，非静态方法填入当前内用this.getClass()即可）
	 * @param classPath 资源文件路径，可以是指定的类（c）为基准的相对路径，也可以是绝对路径。绝对路径以/开头，表示jar包的根目录
	 * @param line      指定的第几行
	 * @return String 为读取的指定行内容
	 * @throws Exception 文件不存在、存在错误或者指定行超出文件固有行数时抛出异常
	 */
	public String readLineInJar(Class c, String classPath, int line) throws Exception {
		String result = "";
		int fl = this.getFileLineCountInJar(c, classPath); // 先获取文件行数
		if (line > fl) {
			throw new RowsOutOfBoundsException("指定的行数超过了文件本来有的行数");
		} else {
			InputStream is = c.getResourceAsStream(classPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			for (int i = 0; i < line; i++) {
				result = br.readLine();
			}
			br.close();
		}
		return result;
	}

	/**
	 * 释放jar包内文件到jar包外部
	 * 
	 * @param c          Class 指定的类（一般是当前类，非静态方法填入当前内用this.getClass()即可）
	 * @param classPath  指定被释放的资源文件路径，可以是指定的类（c）为基准的相对路径，也可以是绝对路径。绝对路径以/开头，表示jar包的根目录
	 * @param outputPath 指定的输出路径
	 * @return boolean 释放成功则为true
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public boolean releaseFileInJar(Class c, String classPath, String outputPath) throws Exception {
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
	public ImageIcon getImageInJar(Class c, String classPath) {
		URL imgurl = c.getResource(classPath);
		return new ImageIcon(imgurl);
	}
	
}