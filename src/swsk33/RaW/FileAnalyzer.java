package swsk33.RaW;

import java.io.*;
import java.math.*;
import java.security.*;


public class FileAnalyzer {
	public String getFileMD5(String filepath) { // 获取文件MD5
		String result = "";
		BigInteger bgi = null;
		try {
			byte[] buffer = new byte[8192];
			int len = 0;
			MessageDigest md = MessageDigest.getInstance("MD5");
			File f = new File(filepath);
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

	public long getFileSizeb(String filepath) { // 获取文件大小（单位B）
		long size = 0;
		File f = new File(filepath);
		size = f.length();
		return size;
	}

	public double getFileSizekb(String filepath) { // 获取文件大小（单位KB）-全显示
		double size = 0;
		File f = new File(filepath);
		size = (double) f.length() / 1024;
		return size;
	}

	public double getFileSizekb(String filepath, int numofdecimres) { // 获取文件大小（单位KB）-保留小数
		double size = 0;
		File f = new File(filepath);
		size = (double) f.length() / 1024;
		BigDecimal bd = new BigDecimal(size);
		size = bd.setScale(numofdecimres, BigDecimal.ROUND_DOWN).doubleValue();
		return size;
	}

	public double getFileSizemb(String filepath) { // 获取文件大小（单位MB）-全部显示
		double size = 0;
		File f = new File(filepath);
		size = (double) f.length() / 1024;
		size = size / 1024;
		return size;
	}

	public double getFileSizemb(String filepath, int numofdecimres) { // 获取文件大小（单位MB）-保留小数
		double size = 0;
		File f = new File(filepath);
		size = (double) f.length() / 1024;
		size = size / 1024;
		BigDecimal bd = new BigDecimal(size);
		size = bd.setScale(numofdecimres, BigDecimal.ROUND_DOWN).doubleValue();
		return size;
	}

	public double getFileSizegb(String filepath) { // 获取文件大小（单位GB）-全部显示
		double size = 0;
		File f = new File(filepath);
		size = (double) f.length() / 1024;
		size = size / 1024;
		size = size / 1024;
		return size;
	}

	public double getFileSizegb(String filepath, int numofdecimres) { // 获取文件大小（单位GB）-保留小数
		double size = 0;
		File f = new File(filepath);
		size = (double) f.length() / 1024;
		size = size / 1024;
		size = size / 1024;
		BigDecimal bd = new BigDecimal(size);
		size = bd.setScale(numofdecimres, BigDecimal.ROUND_FLOOR).doubleValue();
		return size;
	}

	public String getFileFormat(String filepath) { // 获取文件格式
		String ffr = "";
		File f = new File(filepath);
		String fn = f.getName();
		if (fn.contains(".")) {
			ffr = fn.substring(fn.lastIndexOf(".") + 1);
		} else {
			System.out.println("文件没有扩展名！");
		}
		return ffr;
	}
}
