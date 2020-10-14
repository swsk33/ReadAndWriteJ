package swsk33.readandwritej;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import swsk33.readandwritej.exception.RowsOutOfBoundsException;

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
		String content = "";
		int line = new LineScanner().getLineCount(filePath);
		if (whichLine > line) {
			throw new RowsOutOfBoundsException("指定写入行数超过了文件的固有行数！");
		} else if (whichLine <= 0) {
			throw new RowsOutOfBoundsException("指定行数不可小于等于0！");
		} else {
			File f = new File(filePath);
			String before = "";
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			for (int i = 0; i < whichLine - 1; i++) {
				before = before + br.readLine() + "\r\n";
			}
			content = before + text + "\r\n";
			br.readLine();
			for (int i = 0; i < line - whichLine; i++) {
				content = content + br.readLine() + "\r\n";
			}
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(content);
			br.close();
			bw.close();
		}
		// 最后检查是否替换成功
		if (new TextReader().readFile(filePath).equals(content)) {
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
		if (new TextReader().readFile(filePath).equals(old + text + "\r\n")) {
			success = true;
		}
		return success;
	}

	/**
	 * 插入文本至指定行之后或者之前
	 * 
	 * @param filePath    待操作文件的相对/绝对路径
	 * @param insertText  待插入的内容
	 * @param whichLine   指定要插入的某一行
	 * @param isAfterLine 是否在这一行之后插入，true则把内容插入至指定行之后，否则插入到某一行之前
	 * @return boolean 是否插入成功
	 * @throws Exception 文件不存在或者行数指定范围错误时抛出异常
	 */
	public boolean insertText(String filePath, String insertText, int whichLine, boolean isAfterLine) throws Exception {
		boolean success = false;
		int line = new LineScanner().getLineCount(filePath);
		String content = "";
		if (whichLine <= 0) {
			throw new RowsOutOfBoundsException("指定行数不可小于等于0！");
		} else if (whichLine > line) {
			throw new RowsOutOfBoundsException("指定行数不可超过文件固有行数！");
		} else {
			File f = new File(filePath);
			String before = "";
			int bounds;
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			if (isAfterLine) {
				bounds = whichLine;
			} else {
				bounds = whichLine - 1;
			}
			for (int i = 0; i < bounds; i++) {
				before = before + br.readLine() + "\r\n";
			}
			content = before + insertText + "\r\n";
			for (int i = 0; i < line - bounds; i++) {
				content = content + br.readLine() + "\r\n";
			}
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(content);
			br.close();
			bw.close();
		}
		// 检查写入是否成功
		if (new TextReader().readFile(filePath).equals(content)) {
			success = true;
		}
		return success;
	}

	/**
	 * 移除某一行的内容
	 * 
	 * @param filePath  待操作文件相对/绝对路径
	 * @param whichLine 指定被移除的行
	 * @return boolean 是否移除成功
	 * @throws Exception 文件不存在或者指定行数范围错误时抛出异常
	 */
	public boolean removeLine(String filePath, int whichLine) throws Exception {
		boolean success = false;
		String content = "";
		int line = new LineScanner().getLineCount(filePath);
		if (whichLine <= 0) {
			throw new RowsOutOfBoundsException("指定行数不可小于等于0！");
		} else if (whichLine > line) {
			throw new RowsOutOfBoundsException("指定行数不可超过文件固有行数！");
		} else {
			File f = new File(filePath);
			String before = "";
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			for (int i = 0; i < whichLine - 1; i++) {
				before = before + br.readLine() + "\r\n";
			}
			br.readLine();
			content = before;
			for (int i = 0; i < line - whichLine; i++) {
				content = content + br.readLine() + "\r\n";
			}
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(content);
			br.close();
			bw.close();
		}
		// 检测移除是否成功
		if (new TextReader().readFile(filePath).equals(content)) {
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