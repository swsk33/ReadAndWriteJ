package swsk33.RaW;

import java.awt.*;
import java.io.*;

public class ImportFont {
	public Font getFont(String filepath, int wordsize) { // �����Դ������ļ��ķ���
		Font font = null;
		File file = new File(filepath);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, file);
			font = font.deriveFont(Font.PLAIN, wordsize);
		} catch (Exception e) {
			return null;
		}
		return font;
	}

	public Font getBoldFont(String filepath, int wordsize) { // �����Դ������ļ��ķ���(�Ӵ���)
		Font font = null;
		File file = new File(filepath);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, file);
			font = font.deriveFont(Font.BOLD, wordsize);
		} catch (Exception e) {
			return null;
		}
		return font;
	}

	public Font getItalicFont(String filepath, int wordsize) { // �����Դ������ļ��ķ���(б��)
		Font font = null;
		File file = new File(filepath);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, file);
			font = font.deriveFont(Font.ITALIC, wordsize);
		} catch (Exception e) {
			return null;
		}
		return font;
	}

	public Font getItalicBoldFont(String filepath, int wordsize) { // �����Դ������ļ��ķ���(б�Ӵ���)
		Font font = null;
		File file = new File(filepath);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, file);
			font = font.deriveFont(Font.ITALIC | Font.BOLD, wordsize);
		} catch (Exception e) {
			return null;
		}
		return font;
	}
}
