package swsk33.RaW;

import java.io.*;
import java.util.*;


public class RandomReader {
	public String ReadRandomly(String filepathr) throws Exception { // 随机读取整个文件的某一行
		String result = null;
		Random r = new Random();
		int liner = r.nextInt(new LineScanner().GetLinage(filepathr)) + 1;
		File f = new File(filepathr);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < liner; i++) {
			result = br.readLine();
		}
		br.close();
		return result;
	}

	public String ReadRandomlyStart(String filepathr, int start) throws Exception { // 从指定行开始随机读取文件后面的某行
		String result = null;
		Random r = new Random();
		int liner = r.nextInt(new LineScanner().GetLinage(filepathr) - start + 1);
		File f = new File(filepathr);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < new LineScanner().GetLinage(filepathr); i++) {
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

	public String ReadRandomlyUntil(String filepathr, int linesa) throws Exception { // 从第一行开始随机读取文本文档到指定行数
		String result = null;
		Random r = new Random();
		int liner = r.nextInt(linesa) + 1;
		File f = new File(filepathr);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < liner; i++) {
			result = br.readLine();
		}
		br.close();
		return result;
	}

	public String RandomAtSpecifiedRanges(String filepathr, int start, int linesb) throws Exception { // 从某行起随机读取后指定行
		String result = null;
		Random r = new Random();
		int liner = r.nextInt(linesb);
		File f = new File(filepathr);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < new LineScanner().GetLinage(filepathr); i++) {
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
