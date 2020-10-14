package swsk33.readandwritej;

import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import swsk33.readandwritej.exception.RowsOutOfBoundsException;

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
		String result = "";
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
	 * 从指定文件的指定行开始至其文件末尾范围内随机读取其中某行（包含开始的那一行）
	 * 
	 * @param filePath 被指定读取文件相对路径/绝对路径
	 * @param start    指定其开始范围
	 * @return String 字符串 随机读取到的内容
	 * @throws Exception 文件不存在或者存在错误、或者指定行数有误时抛出异常
	 */
	public String readRandomlyStart(String filePath, int start) throws Exception {
		String result = "";
		int line = new LineScanner().getLineCount(filePath);
		if (start <= 0) {
			throw new RowsOutOfBoundsException("起始行数不能小于等于0！");
		} else {
			Random r = new Random();
			int liner = r.nextInt(line - start + 1);
			File f = new File(filePath);
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			for (int i = 0; i < start - 1; i++) {
				br.readLine();
			}
			for (int i = 0; i < liner; i++) {
				result = br.readLine();
			}
			br.close();
		}
		return result;
	}

	/**
	 * 从第一行开始随机读取文本文档前指定行数内一行
	 * 
	 * @param filePath 被指定读取文件相对路径/绝对路径
	 * @param end      结束行数
	 * @return String 字符串 随机读取到的内容
	 * @throws Exception 文件不存在或者存在错误、指定行数有误时抛出异常
	 */
	public String readRandomlyUntil(String filePath, int end) throws Exception {
		String result = "";
		int line = new LineScanner().getLineCount(filePath);
		if (end > line) {
			throw new RowsOutOfBoundsException("终止行数不能大于文件固有行数！");
		} else {
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
		}
		return result;
	}

	/**
	 * 从某行起随机读取后指定行数内的某一行
	 * 
	 * @param filePath 被指定读取文件相对路径/绝对路径
	 * @param start    指定的开始行数
	 * @param end      指定的结束行数
	 * @return String 字符串 随机读取到的内容
	 * @throws Exception 文件不存在或者存在错误、或者指定行数有误时抛出异常
	 */
	public String randomAtSpecifiedRanges(String filePath, int start, int end) throws Exception {
		String result = "";
		int line = new LineScanner().getLineCount(filePath);
		if (start <= 0 || end <= 0) {
			throw new RowsOutOfBoundsException("起始行数或者终止行数不可小于等于0！");
		} else if (start > line || end > line) {
			throw new RowsOutOfBoundsException("起始行数或者终止行数不可大于文件固有行数！");
		} else if (start > end) {
			throw new RowsOutOfBoundsException("起始行数不可大于终止行数！");
		} else {
			Random r = new Random();
			int liner = r.nextInt(end);
			File f = new File(filePath);
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			for (int i = 0; i < new LineScanner().getLineCount(filePath); i++) {
				result = br.readLine();
				if (i == start) {
					break;
				}
			}
			for (int i = 0; i < liner; i++) {
				result = br.readLine();
			}
			br.close();
		}
		return result;
	}

}