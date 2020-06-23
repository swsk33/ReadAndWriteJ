package swsk33.RaW;

import java.io.*;
import java.util.*;

public class TextReader {
	public String ReadText(String filepath, int line) throws Exception { // ��ȡ�ı��ĵ�ָ����
		String result = "";
		File f = new File(filepath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < line; i++) {
			result = br.readLine();
		}
		br.close();
		return result;
	}

	public String ReadFileRange(String filepath, int start, int end) throws Exception { // ָ����ȡ��Χ�ض�ȡ�ļ������䴢�����ַ�����
		String result = "";
		int fileline = new LineScanner().GetLinage(filepath);
		if (start <= 0) {
			System.out.println("��ʼ��������С�ڵ���0��");
		} else if (start > fileline) {
			System.out.println("��ʼ�������ܴ����ĵ�����������");
		} else if (end <= 0) {
			System.out.println("��ֹ��������С�ڵ���0��");
		} else if (end > fileline) {
			System.out.println("��ֹ�������ܴ����ĵ�����������");
		} else if (start > end) {
			System.out.println("��ֹ��������С����ʼ������");
		} else {
			File f = new File(filepath);
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			int i;
			for (i = 0; i < start; i++) {
				result = br.readLine();
			}
			for (int i1 = i; i1 < end; i1++) {
				result = result + "\r\n" + br.readLine();
			}
			br.close();
		}
		return result;
	}

	public String[] ReadFileRangeToArray(String filepath, int start, int end) throws Exception { // ָ����ȡ��Χ�ض�ȡ�ļ������䴢�����ַ���������
		ArrayList<String> rdi = new ArrayList<String>();
		int fileline = new LineScanner().GetLinage(filepath);
		if (start <= 0) {
			System.out.println("��ʼ��������С�ڵ���0��");
		} else if (start > fileline) {
			System.out.println("��ʼ�������ܴ����ĵ�����������");
		} else if (end <= 0) {
			System.out.println("��ֹ��������С�ڵ���0��");
		} else if (end > fileline) {
			System.out.println("��ֹ�������ܴ����ĵ�����������");
		} else if (start > end) {
			System.out.println("��ֹ��������С����ʼ������");
		} else {
			File f = new File(filepath);
			String rd = "";
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			int i;
			for (i = 0; i < start; i++) {
				rd = br.readLine();
			}
			rdi.add(rd);
			for (int i1 = i; i1 < end; i1++) {
				rd = br.readLine();
				rdi.add(rd);
			}
			br.close();
		}
		String[] result = (String[]) rdi.toArray(new String[rdi.size()]);
		return result;
	}

	public String ReadFile(String filepath) throws Exception { // ��ȡ�����ı��ĵ��������ݴ������ַ�����
		String result = "";
		File f = new File(filepath);
		int linef = new LineScanner().GetLinage(filepath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < linef; i++) {
			result = result + br.readLine() + "\r\n";
		}
		br.close();
		return result;
	}

	public String[] ReadFileToArray(String filepath) throws Exception { // ��ȡ�����ı��ĵ��������ݴ������ַ���������
		ArrayList<String> rdi = new ArrayList<String>();
		File f = new File(filepath);
		int linef = new LineScanner().GetLinage(filepath);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		for (int i = 0; i < linef; i++) {
			rdi.add(br.readLine());
		}
		br.close();
		String[] result = (String[]) rdi.toArray(new String[rdi.size()]);
		return result;
	}
}
