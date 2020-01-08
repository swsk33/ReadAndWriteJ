package swsk33.RaW;
import java.io.*;
public class TextReader {
	public String ReadText(String filepath,int line) throws Exception {		//��ȡ�ı��ĵ�ָ����
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
	public String ReadFileStart(String filepath,int linestart) throws Exception {		//���ļ���ĳһ�п�ʼ��ȡ�����������ݲ����䴢�����ַ�����
		String result=null;
		int fileline=new LineScanner().GetLinage(filepath);
		if(linestart<=0) {
			System.out.println("��ʼ��������С�ڵ���0��");
		} else if(linestart>fileline) {
			System.out.println("��ʼ�������ܴ����ĵ�����������");
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
		}
		return result;
	}
	public String ReadFile(String filepath) throws Exception {		//��ȡ�����ı��ĵ��������ݴ������ַ�����
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
