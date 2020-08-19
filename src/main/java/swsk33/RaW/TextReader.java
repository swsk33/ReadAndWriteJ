package swsk33.RaW;

import java.io.*;
import java.util.*;

/**
 * 文件读取器
 * 
 * @author swsk33
 *
 */
public class TextReader {
	/**
	 * 读取文本文件指定行内容
	 * 
	 * @param filePath 待读取文件相对/绝对路径
	 * @param line     待读取的行数
	 * @return String 字符串 读取的内容
	 * @throws Exception 文件存在错误或者文件不存在时抛出异常
	 */
	public String readText(String filePath, int line) throws Exception {
		String result = "";
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < line; i++) {
			result = br.readLine();
		}
		br.close();
		return result;
	}

	/**
	 * 读取指定行数范围内的内容并以字符串形式储存
	 * 
	 * @param filePath 待读取文件相对/绝对路径
	 * @param start    指定起始行
	 * @param end      指定终止行
	 * @return String 字符串 读取的内容
	 * @throws Exception 文件存在错误或者文件不存在时抛出异常
	 */
	public String readFileRange(String filePath, int start, int end) throws Exception {
		String result = "";
		int fileline = new LineScanner().getLineCount(filePath);
		if (start <= 0) {
			System.out.println("起始行数不能小于等于0！");
		} else if (start > fileline) {
			System.out.println("起始行数不能大于文档的总行数！");
		} else if (end <= 0) {
			System.out.println("终止行数不能小于等于0！");
		} else if (end > fileline) {
			System.out.println("终止行数不能大于文档的总行数！");
		} else if (start > end) {
			System.out.println("终止行数不能小于起始行数！");
		} else {
			File f = new File(filePath);
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			int i;
			for (i = 0; i < start; i++) {
				result = br.readLine();
			}
			for (int i1 = i; i1 < end; i1++) {
				result = result + "\r\n" + br.readLine();
			}
			br.close();
		}
		return result;
	}

	/**
	 * 读取指定行数范围内的内容并以字符串数组形式储存 每一行的内容即为数组中的一个String元素
	 * 
	 * @param filePath 待读取文件相对/绝对路径
	 * @param start    指定起始行
	 * @param end      指定终止行
	 * @return String[] 字符串数组 读取的内容
	 * @throws Exception 文件存在错误或者文件不存在时抛出异常
	 */
	public String[] readFileRangeToArray(String filePath, int start, int end) throws Exception {
		ArrayList<String> rdi = new ArrayList<String>();
		int fileline = new LineScanner().getLineCount(filePath);
		if (start <= 0) {
			System.out.println("起始行数不能小于等于0！");
		} else if (start > fileline) {
			System.out.println("起始行数不能大于文档的总行数！");
		} else if (end <= 0) {
			System.out.println("终止行数不能小于等于0！");
		} else if (end > fileline) {
			System.out.println("终止行数不能大于文档的总行数！");
		} else if (start > end) {
			System.out.println("终止行数不能小于起始行数！");
		} else {
			File f = new File(filePath);
			String rd = "";
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			int i;
			for (i = 0; i < start; i++) {
				rd = br.readLine();
			}
			rdi.add(rd);
			for (int i1 = i; i1 < end; i1++) {
				rd = br.readLine();
				rdi.add(rd);
			}
			br.close();
		}
		String[] result = (String[]) rdi.toArray(new String[rdi.size()]);
		return result;
	}

	/**
	 * 读取整个文本文档并将内容储存在字符串中
	 * 
	 * @param filePath 待读取文件相对/绝对路径
	 * @return String 字符串 读取的内容
	 * @throws Exception 文件存在错误或者文件不存在时抛出异常
	 */
	public String readFile(String filePath) throws Exception {
		String result = "";
		File f = new File(filePath);
		int linef = new LineScanner().getLineCount(filePath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < linef; i++) {
			result = result + br.readLine() + "\r\n";
		}
		br.close();
		return result;
	}

	/**
	 * 读取整个文本文档并将内容储存在字符串数组中 每一行的内容都是String数组中的一个元素
	 * 
	 * @param filePath
	 * @return String[] 字符串数组 读取的内容
	 * @throws Exception 文件存在错误或者文件不存在时抛出异常
	 */
	public String[] readFileToArray(String filePath) throws Exception {
		ArrayList<String> rdi = new ArrayList<String>();
		File f = new File(filePath);
		int linef = new LineScanner().getLineCount(filePath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < linef; i++) {
			rdi.add(br.readLine());
		}
		br.close();
		String[] result = (String[]) rdi.toArray(new String[rdi.size()]);
		return result;
	}
}
