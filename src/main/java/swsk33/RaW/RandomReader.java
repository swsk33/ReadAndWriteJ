package swsk33.RaW;

import java.io.*;
import java.util.*;

/**
 * 文件随机读取器
 * 
 * @author swsk33
 *
 */
public class RandomReader {
	/**
	 * 随机读取整个被指定文件的某一行
	 * 
	 * @param filePath 被指定读取文件相对路径/绝对路径
	 * @return String 字符串 随机读取到的内容
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public String readRandomly(String filePath) throws Exception {
		String result = null;
		Random r = new Random();
		int liner = r.nextInt(new LineScanner().getLineCount(filePath)) + 1;
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < liner; i++) {
			result = br.readLine();
		}
		br.close();
		return result;
	}

	/**
	 * 从指定文件的指定行开始至其文件末尾范围内随机读取其中某行
	 * 
	 * @param filePath 被指定读取文件相对路径/绝对路径
	 * @param start    指定其开始范围
	 * @return String 字符串 随机读取到的内容
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public String readRandomlyStart(String filePath, int start) throws Exception {
		String result = null;
		Random r = new Random();
		int liner = r.nextInt(new LineScanner().getLineCount(filePath) - start + 1);
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < new LineScanner().getLineCount(filePath); i++) {
			result = br.readLine();
			if (i == start - 1) {
				break;
			}
		}
		for (int i = 0; i < liner; i++) {
			result = br.readLine();
		}
		br.close();
		return result;
	}

	/**
	 * 从第一行开始随机读取文本文档前指定行数内一行
	 * 
	 * @param filePath 被指定读取文件相对路径/绝对路径
	 * @param end      结束行数
	 * @return String 字符串 随机读取到的内容
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public String readRandomlyUntil(String filePath, int end) throws Exception {
		String result = null;
		Random r = new Random();
		int liner = r.nextInt(end) + 1;
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < liner; i++) {
			result = br.readLine();
		}
		br.close();
		return result;
	}

	/**
	 * 从某行起随机读取后指定行数内的某一行
	 * 
	 * @param filepath 被指定读取文件相对路径/绝对路径
	 * @param start    指定的开始行数
	 * @param end      指定的结束行数
	 * @return String 字符串 随机读取到的内容
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public String randomAtSpecifiedRanges(String filepath, int start, int end) throws Exception {
		String result = null;
		Random r = new Random();
		int liner = r.nextInt(end);
		File f = new File(filepath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < new LineScanner().getLineCount(filepath); i++) {
			result = br.readLine();
			if (i == start) {
				break;
			}
		}
		for (int i = 0; i < liner; i++) {
			result = br.readLine();
		}
		br.close();
		return result;
	}
}
