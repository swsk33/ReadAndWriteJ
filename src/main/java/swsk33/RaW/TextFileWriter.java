package swsk33.RaW;

import java.io.*;
import swsk33.RaW.Exception.*;

/**
 * 文件写入器
 * 
 * @author swsk33
 *
 */
public class TextFileWriter {
	/**
	 * 用指定内容替换文件指定行
	 * 
	 * @param filePath  待写入文件相对/绝对路径
	 * @param whichLine 待替换的行数
	 * @param text      待替换的内容
	 * @return boolean 替换写入操作成功即返回true
	 * @throws Exception 文件不存在或者存在错误、指定替换的行数超出文件固有行数或者小于0时抛出异常
	 */
	public boolean replaceLine(String filePath, int whichLine, String text) throws Exception {
		boolean success = false;
		int fl = new LineScanner().getLineCount(filePath);
		if (whichLine > fl) {
			throw new RowsOutOfBoundsException("指定写入行数超过了文件的固有行数！");
		} else if (whichLine <= 0) {
			throw new RowsOutOfBoundsException("错误！指定行数不可小于等于0！");
		} else {
			File f = new File(filePath);
			String sumstr = "";
			String front = "";
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			int ifr;
			for (ifr = 0; ifr < whichLine - 1; ifr++) {
				front = front + br.readLine() + "\r\n";
			}
			sumstr = front + text + "\r\n";
			br.readLine();
			for (int iaf = ifr + 1; iaf < fl; iaf++) {
				sumstr = sumstr + br.readLine() + "\r\n";
			}
			br.close();
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(sumstr);
			bw.close();
		}
		// 最后检查是否替换成功
		if (new TextReader().readText(filePath, whichLine).equals(text)) {
			success = true;
		}
		return success;
	}

	/**
	 * 写入指定内容至文件末尾，每执行一次该方法就在末尾写入一行内容
	 * 
	 * @param filePath 待写入文件相对/绝对路径
	 * @param text     待写入内容
	 * @return boolean 写入操作成功即返回true
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public boolean writeText(String filePath, String text) throws Exception {
		boolean success = false;
		File f = new File(filePath);
		int line = new LineScanner().getLineCount(filePath);
		String old = "";
		if (!(line == 0)) {
			for (int i = 0; i < line; i++) {
				old = old + new TextReader().readText(filePath, i + 1) + "\r\n";
			}
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(old);
			bw.write(text);
			bw.close();
		} else {
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(text);
			bw.close();
		}
		// 检查是否写入成功
		if (new TextReader().readText(filePath, line + 1).equals(text)) {
			success = true;
		}
		return success;
	}

	/**
	 * 把指定文件内容清空
	 * 
	 * @param filePath 待清空文件相对/绝对路径
	 * @throws Exception 文件不存在时抛出异常
	 * @return boolean 清空操作成功时返回true
	 */
	public boolean clearAll(String filePath) throws Exception {
		boolean success = false;
		File f = new File(filePath);
		FileOutputStream fos = new FileOutputStream(f);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("");
		bw.close();
		// 检查是否操作成功
		if (new LineScanner().getLineCount(filePath) == 0) {
			success = true;
		}
		return success;
	}
}