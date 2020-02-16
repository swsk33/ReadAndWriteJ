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
	public String ReadFileStart(String filepath,int linestart) throws Exception {		//从文件的某一行开始读取后面所有内容并将其储存在字符串中
		String result=null;
		int fileline=new LineScanner().GetLinage(filepath);
		if(linestart<=0) {
			System.out.println("起始行数不能小于等于0！");
		} else if(linestart>fileline) {
			System.out.println("起始行数不能大于文档的总行数！");
		} else {
			File f=new File(filepath);
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(isr);
			int i;
			for(i=0;i<linestart;i++) {
				result=br.readLine();
			}
			for(int i1=i;i1<fileline;i1++) {
				result=result+"\r\n"+br.readLine();
			}
			br.close();
		}
		return result;
	}
	public String ReadFileUntil(String filepath,int lineuntil) throws Exception {		//读取文件指定行以前的所有内容并将其储存在字符串中
		String result=null;
		int fileline=new LineScanner().GetLinage(filepath);
		if(lineuntil<=0) {
			System.out.println("终止行数不能小于等于0！");
		} else if(lineuntil>fileline) {
			System.out.println("终止行数不能大于文档行数！");
		} else {
			File f=new File(filepath);
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(isr);
			result=br.readLine();
			for(int i=1;i<lineuntil;i++) {
				result=result+"\r\n"+br.readLine();
			}
			br.close();
		}
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
			br.close();
			result=cdr;
		}
		return result;
	}
	public String ReadTextUTF8(String filepath,int line) throws Exception {		//读取文本文档指定行-UTF-8形式
		String result=null;
		File f=new File(filepath);
		FileInputStream fis=new FileInputStream(f);
		InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
		BufferedReader br=new BufferedReader(isr);
		for(int i=0;i<line;i++) {
			result=br.readLine();
		}
		br.close();
		return result;
	}
	public String ReadFileStartUTF8(String filepath,int linestart) throws Exception {		//从文件的某一行开始读取后面所有内容并将其储存在字符串中-UTF-8形式
		String result=null;
		int fileline=new LineScanner().GetLinage(filepath);
		if(linestart<=0) {
			System.out.println("起始行数不能小于等于0！");
		} else if(linestart>fileline) {
			System.out.println("起始行数不能大于文档的总行数！");
		} else {
			File f=new File(filepath);
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
			BufferedReader br=new BufferedReader(isr);
			int i;
			for(i=0;i<linestart;i++) {
				result=br.readLine();
			}
			for(int i1=i;i1<fileline;i1++) {
				result=result+"\r\n"+br.readLine();
			}
			br.close();
		}
		return result;
	}
	public String ReadFileUntilUTF8(String filepath,int lineuntil) throws Exception {		//读取文件指定行以前的所有内容并将其储存在字符串中-UTF-8形式
		String result=null;
		int fileline=new LineScanner().GetLinage(filepath);
		if(lineuntil<=0) {
			System.out.println("终止行数不能小于等于0！");
		} else if(lineuntil>fileline) {
			System.out.println("终止行数不能大于文档行数！");
		} else {
			File f=new File(filepath);
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
			BufferedReader br=new BufferedReader(isr);
			result=br.readLine();
			for(int i=1;i<lineuntil;i++) {
				result=result+"\r\n"+br.readLine();
			}
			br.close();
		}
		return result;
	}
	public String ReadFileUTF8(String filepath) throws Exception {		//读取整个文本文档并将内容储存在字符串中-UTF-8形式
		String result=null;
		String cdr="";
		File f=new File(filepath);
		int linef=new LineScanner().GetLinage(filepath);
		if(linef==0) {
			result=null;
		} else {
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
			BufferedReader br=new BufferedReader(isr);
			for(int i=0;i<linef;i++) {
				cdr=cdr+br.readLine()+"\r\n";
			}
			br.close();
			result=cdr;
		}
		return result;
	}
	public String ReadTextGBK(String filepath,int line) throws Exception {		//读取文本文档指定行-GBK形式
		String result=null;
		File f=new File(filepath);
		FileInputStream fis=new FileInputStream(f);
		InputStreamReader isr=new InputStreamReader(fis,"GBK");
		BufferedReader br=new BufferedReader(isr);
		for(int i=0;i<line;i++) {
			result=br.readLine();
		}
		br.close();
		return result;
	}
	public String ReadFileStartGBK(String filepath,int linestart) throws Exception {		//从文件的某一行开始读取后面所有内容并将其储存在字符串中-GBK形式
		String result=null;
		int fileline=new LineScanner().GetLinage(filepath);
		if(linestart<=0) {
			System.out.println("起始行数不能小于等于0！");
		} else if(linestart>fileline) {
			System.out.println("起始行数不能大于文档的总行数！");
		} else {
			File f=new File(filepath);
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis,"GBK");
			BufferedReader br=new BufferedReader(isr);
			int i;
			for(i=0;i<linestart;i++) {
				result=br.readLine();
			}
			for(int i1=i;i1<fileline;i1++) {
				result=result+"\r\n"+br.readLine();
			}
			br.close();
		}
		return result;
	}
	public String ReadFileUntilGBK(String filepath,int lineuntil) throws Exception {		//读取文件指定行以前的所有内容并将其储存在字符串中-GBK形式
		String result=null;
		int fileline=new LineScanner().GetLinage(filepath);
		if(lineuntil<=0) {
			System.out.println("终止行数不能小于等于0！");
		} else if(lineuntil>fileline) {
			System.out.println("终止行数不能大于文档行数！");
		} else {
			File f=new File(filepath);
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis,"GBK");
			BufferedReader br=new BufferedReader(isr);
			result=br.readLine();
			for(int i=1;i<lineuntil;i++) {
				result=result+"\r\n"+br.readLine();
			}
			br.close();
		}
		return result;
	}
	public String ReadFileGBK(String filepath) throws Exception {		//读取整个文本文档并将内容储存在字符串中-GBK形式
		String result=null;
		String cdr="";
		File f=new File(filepath);
		int linef=new LineScanner().GetLinage(filepath);
		if(linef==0) {
			result=null;
		} else {
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis,"GBK");
			BufferedReader br=new BufferedReader(isr);
			for(int i=0;i<linef;i++) {
				cdr=cdr+br.readLine()+"\r\n";
			}
			br.close();
			result=cdr;
		}
		return result;
	}
}
