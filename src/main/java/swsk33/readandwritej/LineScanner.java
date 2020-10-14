package swsk33.readandwritej;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * 文件行数读取器
 * 
 * @author swsk33
 *
 */
public class LineScanner {

	/**
	 * 获取文本文件的内容行数
	 * 
	 * @param filePath 待读取文件的相对路径/绝对路径
	 * @return int 整型 文件行数
	 * @throws Exception 文件不存在或存在错误时抛出异常
	 */
	public int getLineCount(String filePath) throws Exception {
		int linage = 0;
		File f = new File(filePath);
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