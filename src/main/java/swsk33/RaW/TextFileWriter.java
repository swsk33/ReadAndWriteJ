package swsk33.RaW;

import java.io.*;

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
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public void replaceLine(String filePath, int whichLine, String text) throws Exception {
		int fl = new LineScanner().getLineCount(filePath);
		if (whichLine > fl) {
			System.out.println("错误！超过文件最大行数！");
		} else if (whichLine <= 0) {
			System.out.println("错误！指定行数不可小于等于0！");
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
	}

	/**
	 * 写入指定内容至文件末尾，每执行一次该方法就在末尾写入一行内容
	 * 
	 * @param filePath 待写入文件相对/绝对路径
	 * @param text     待写入内容
	 * @throws Exception 文件不存在或者存在错误时抛出异常
	 */
	public void writeText(String filePath, String text) throws Exception {
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
	}

	/**
	 * 把指定文件内容清空
	 * 
	 * @param filePath 待清空文件相对/绝对路径
	 * @throws Exception 文件不存在时抛出异常
	 */
	public void clearAll(String filePath) throws Exception {
		File f = new File(filePath);
		FileOutputStream fos = new FileOutputStream(f);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("");
		bw.close();
	}
}