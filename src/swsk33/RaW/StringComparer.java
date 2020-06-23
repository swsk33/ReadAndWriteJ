package swsk33.RaW;

import java.io.*;

public class StringComparer {
	public boolean CompareLine(String filepathc, String LineCompared) throws Exception { // �Ƚ����������Ƿ��뱻�Ƚ��ַ���һ��
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

	public boolean CompareText(String filepatht, String TextCompared) throws Exception { // �ж��ı��ĵ����Ƿ�������Ƚϵ��ַ���
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
