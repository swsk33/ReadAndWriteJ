package swsk33.RaW;

import java.io.*;

public class LineScanner {
	public int GetLinage(String filepath) throws Exception { // ��ȡ�ı��ĵ�����
		int linage = 0;
		File f = new File(filepath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 1; br.readLine() != null; i++) {
			linage = i;
		}
		br.close();
		return linage;
	}
}
