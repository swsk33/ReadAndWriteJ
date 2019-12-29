package swsk33.RaW;
import java.io.*;
public class TextReader {
	public String ReadText(String filepath,int line) throws Exception {		//读取文本文档指定行
		String result=null;
		File f=new File(filepath);
		FileInputStream fis=new FileInputStream(f);
		InputStreamReader isr=new InputStreamReader(fis);
		BufferedReader br=new BufferedReader(isr);
		for(int i=0;i<line;i++) {
			result=br.readLine();
		}
		br.close();
		return result;
	}
	public String ReadFile(String filepath) throws Exception {		//读取整个文本文档并将内容储存在字符串中
		String result=null;
		String cdr="";
		File f=new File(filepath);
		int linef=new LineScanner().GetLinage(filepath);
		if(linef==0) {
			result=null;
		} else {
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(isr);
			for(int i=0;i<linef;i++) {
				cdr=cdr+br.readLine()+"\r\n";
			}
			result=cdr;
		}
		return result;
	}
}
