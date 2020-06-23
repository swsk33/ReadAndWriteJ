package swsk33.RaW;

import java.io.*;

public class StringComparer {
	public boolean CompareLine(String filepathc, String LineCompared) throws Exception { // 比较整行内容是否与被比较字符串一致
		boolean result = false;
		int line = new LineScanner().GetLinage(filepathc);
		File f = new File(filepathc);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < line; i++) {
			if (br.readLine().equals(LineCompared)) {
				result = true;
				break;
			}
		}
		br.close();
		return result;
	}

	public boolean CompareText(String filepatht, String TextCompared) throws Exception { // 判断文本文档内是否包含被比较的字符串
		boolean result = false;
		int line = new LineScanner().GetLinage(filepatht);
		File f = new File(filepatht);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < line; i++) {
			if (br.readLine().contains(TextCompared)) {
				result = true;
				break;
			}
		}
		br.close();
		return result;
	}
}
