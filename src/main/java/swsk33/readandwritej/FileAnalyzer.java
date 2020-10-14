package swsk33.readandwritej;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 文件分析器，用于读取文件大小和格式（扩展名）
 * 
 * @author swsk33
 */
public class FileAnalyzer {

	/**
	 * 用于获取文件的MD5值
	 * 
	 * @param filePath 文件的相对路径/绝对路径
	 * @return String 字符串 文件大小
	 */
	public String getFileMD5(String filePath) {
		String result = "";
		BigInteger bgi = null;
		try {
			byte[] buffer = new byte[8192];
			int len = 0;
			MessageDigest md = MessageDigest.getInstance("MD5");
			File f = new File(filePath);
			FileInputStream fis = new FileInputStream(f);
			while ((len = fis.read(buffer)) != -1) {
				md.update(buffer, 0, len);
			}
			fis.close();
			byte[] b = md.digest();
			bgi = new BigInteger(1, b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = bgi.toString(16);
		return result;
	}

	/**
	 * 获取文件的大小，单位为B
	 * 
	 * @param filePath 文件的相对路径/绝对路径
	 * @return long 长整型 文件大小
	 */
	public long getFileSizeb(String filePath) {
		long size = 0;
		File f = new File(filePath);
		size = f.length();
		return size;
	}

	/**
	 * 获取文件的大小，单位为KB
	 * 
	 * @param filePath 文件的相对路径/绝对路径
	 * @return double 浮点型 文件大小
	 */
	public double getFileSizekb(String filePath) {
		double size = 0;
		File f = new File(filePath);
		size = (double) f.length() / 1024;
		return size;
	}

	/**
	 * 获取文件大小，单位为KB-保留小数
	 * 
	 * @param filePath      文件的相对路径/绝对路径
	 * @param numofdecimres 要保留的小数位数
	 * @return double 浮点型 文件大小
	 */
	public double getFileSizekb(String filePath, int numofdecimres) {
		double size = 0;
		File f = new File(filePath);
		size = (double) f.length() / 1024;
		BigDecimal bd = new BigDecimal(size);
		size = bd.setScale(numofdecimres, BigDecimal.ROUND_DOWN).doubleValue();
		return size;
	}

	/**
	 * 获取文件的大小，单位为MB
	 * 
	 * @param filePath 文件的相对路径/绝对路径
	 * @return double 浮点型 文件大小
	 */
	public double getFileSizemb(String filePath) {
		double size = 0;
		File f = new File(filePath);
		size = (double) f.length() / 1024;
		size = size / 1024;
		return size;
	}

	/**
	 * 获取文件大小，单位为MB-保留小数
	 * 
	 * @param filePath      文件的相对路径/绝对路径
	 * @param numofdecimres 要保留的小数位数
	 * @return double 浮点型 文件大小
	 */
	public double getFileSizemb(String filePath, int numofdecimres) {
		double size = 0;
		File f = new File(filePath);
		size = (double) f.length() / 1024;
		size = size / 1024;
		BigDecimal bd = new BigDecimal(size);
		size = bd.setScale(numofdecimres, BigDecimal.ROUND_DOWN).doubleValue();
		return size;
	}

	/**
	 * 获取文件的大小，单位为GB
	 * 
	 * @param filePath 文件的相对路径/绝对路径
	 * @return double 浮点型 文件大小
	 */
	public double getFileSizegb(String filePath) {
		double size = 0;
		File f = new File(filePath);
		size = (double) f.length() / 1024;
		size = size / 1024;
		size = size / 1024;
		return size;
	}

	/**
	 * 获取文件大小，单位为GB-保留小数
	 * 
	 * @param filePath      文件的相对路径/绝对路径
	 * @param numofdecimres 要保留的小数位数
	 * @return double 浮点型 文件大小
	 */
	public double getFileSizegb(String filePath, int numofdecimres) {
		double size = 0;
		File f = new File(filePath);
		size = (double) f.length() / 1024;
		size = size / 1024;
		size = size / 1024;
		BigDecimal bd = new BigDecimal(size);
		size = bd.setScale(numofdecimres, BigDecimal.ROUND_FLOOR).doubleValue();
		return size;
	}

	/**
	 * 获取文件格式（扩展名）
	 * 
	 * @param filePath 文件的相对路径/绝对路径
	 * @return String 字符串 为获取文件扩展名（不带.）
	 */
	public String getFileFormat(String filePath) {
		String ffr = "";
		File f = new File(filePath);
		String fn = f.getName();
		if (fn.contains(".")) {
			ffr = fn.substring(fn.lastIndexOf(".") + 1);
		} else {
			System.out.println("文件没有扩展名！");
		}
		return ffr;
	}

}