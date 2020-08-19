package swsk33.RaW;

import java.io.*;

public class TextFileWriter {
	public void replaceLine(String filepath, int whichline, String Text) throws Exception { // 用指定内容替换文件指定行
		int fl = new LineScanner().GetLinage(filepath);
		if (whichline > fl) {
			System.out.println("错误！超过文件最大行数！");
		} else if (whichline <= 0) {
			System.out.println("错误！指定行数不可小于等于0！");
		} else {
			File f = new File(filepath);
			String sumstr = "";
			String front = "";
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			int ifr;
			for (ifr = 0; ifr < whichline - 1; ifr++) {
				front = front + br.readLine() + "\r\n";
			}
			sumstr = front + Text + "\r\n";
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

	public void writeText(String filepath, String Text) throws Exception { // 写入指定内容至文件
		File f = new File(filepath);
		int line = new LineScanner().GetLinage(filepath);
		String old = "";
		if (!(line == 0)) {
			for (int i = 0; i < line; i++) {
				old = old + new TextReader().ReadText(filepath, i + 1) + "\r\n";
			}
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(old);
			bw.write(Text);
			bw.close();
		} else {
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(Text);
			bw.close();
		}
	}

	public void clearAll(String filepath) throws Exception { // 把一个文件内容清空
		File f = new File(filepath);
		FileOutputStream fos = new FileOutputStream(f);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("");
		bw.close();
	}
}