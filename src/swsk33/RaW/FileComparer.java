package swsk33.RaW;

import java.io.*;

public class FileComparer {
	public boolean comFile(String Filepath1, String Filepath2) throws Exception { // �Ƚ������ļ��Ƿ���ȫһ��
		boolean result = false;
		File f1 = new File(Filepath1); // ��ȡ��һ���ļ�
		int fl1 = new LineScanner().GetLinage(Filepath1);
		String s1 = "";
		FileInputStream fis1 = new FileInputStream(f1);
		InputStreamReader isr1 = new InputStreamReader(fis1);
		BufferedReader br1 = new BufferedReader(isr1);
		for (int i1 = 0; i1 < fl1; i1++) {
			s1 = s1 + br1.readLine() + "\r\n";
		}
		br1.close();
		File f2 = new File(Filepath2); // ��ȡ�ڶ����ļ�
		int fl2 = new LineScanner().GetLinage(Filepath2);
		String s2 = "";
		FileInputStream fis2 = new FileInputStream(f2);
		InputStreamReader isr2 = new InputStreamReader(fis2);
		BufferedReader br2 = new BufferedReader(isr2);
		for (int i2 = 0; i2 < fl2; i2++) {
			s2 = s2 + br2.readLine() + "\r\n";
		}
		br2.close();
		if (s1.equals(s2)) {
			result = true;
		}
		return result;
	}
}
