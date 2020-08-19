package swsk33.RaW;

import java.io.*;
import java.util.*;

public class TextReader {
	public String ReadText(String filepath, int line) throws Exception { // 读取文本文档指定行
		String result = "";
		File f = new File(filepath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < line; i++) {
			result = br.readLine();
		}
		br.close();
		return result;
	}

	public String ReadFileRange(String filepath, int start, int end) throws Exception { // 指定读取范围地读取文件并将其储存在字符串中
		String result = "";
		int fileline = new LineScanner().GetLinage(filepath);
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
			File f = new File(filepath);
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

	public String[] ReadFileRangeToArray(String filepath, int start, int end) throws Exception { // 指定读取范围地读取文件并将其储存在字符串数组中
		ArrayList<String> rdi = new ArrayList<String>();
		int fileline = new LineScanner().GetLinage(filepath);
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
			File f = new File(filepath);
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

	public String ReadFile(String filepath) throws Exception { // 读取整个文本文档并将内容储存在字符串中
		String result = "";
		File f = new File(filepath);
		int linef = new LineScanner().GetLinage(filepath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < linef; i++) {
			result = result + br.readLine() + "\r\n";
		}
		br.close();
		return result;
	}

	public String[] ReadFileToArray(String filepath) throws Exception { // 读取整个文本文档并将内容储存在字符串数组中
		ArrayList<String> rdi = new ArrayList<String>();
		File f = new File(filepath);
		int linef = new LineScanner().GetLinage(filepath);
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
