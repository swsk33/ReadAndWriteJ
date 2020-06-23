package swsk33.RaW;

import java.io.*;
import java.util.*;


public class RandomReader {
	public String ReadRandomly(String filepathr) throws Exception { // �����ȡ�����ļ���ĳһ��
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

	public String ReadRandomlyStart(String filepathr, int start) throws Exception { // ��ָ���п�ʼ�����ȡ�ļ������ĳ��
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

	public String ReadRandomlyUntil(String filepathr, int linesa) throws Exception { // �ӵ�һ�п�ʼ�����ȡ�ı��ĵ���ָ������
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

	public String RandomAtSpecifiedRanges(String filepathr, int start, int linesb) throws Exception { // ��ĳ���������ȡ��ָ����
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
