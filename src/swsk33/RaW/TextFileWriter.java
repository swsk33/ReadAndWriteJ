package swsk33.RaW;
import java.io.*;
public class TextFileWriter {		//用指定内容替换文件指定行
	public void replaceLine(String filepath,int whichline,String Text) throws Exception {
        String temp="";
        if(whichline<=new LineScanner().GetLinage(filepath)) {
        	try {
        		File f=new File(filepath);
        		FileInputStream fis=new FileInputStream(f);
        		InputStreamReader isr=new InputStreamReader(fis);
        		BufferedReader br=new BufferedReader(isr);
        		StringBuffer buf=new StringBuffer();
        		for (int j=1; (temp=br.readLine())!=null;j++) {
        			if(j==whichline){
        				buf=buf.append(Text);
        			}else{
        				buf=buf.append(temp);
        			}
        			buf=buf.append(System.getProperty("line.separator"));
        		}
        		br.close();
        		FileOutputStream fos=new FileOutputStream(f);
        		PrintWriter pw=new PrintWriter(fos);
        		pw.write(buf.toString().toCharArray());
        		pw.flush();
        		pw.close();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        }else if(whichline>new LineScanner().GetLinage(filepath)){
        	System.out.println("错误！超过文件最大行数！");
        }
	}
	public void writeText(String filepath,String Text) throws Exception {		//写入指定内容至文件
		File f=new File(filepath);
		int line=new LineScanner().GetLinage(filepath);
		String old="";
		if(!(line==0)) {
			for(int i=0;i<line;i++) {
				old=old+new TextReader().ReadText(filepath,i+1)+"\r\n";
			}
			FileOutputStream fos=new FileOutputStream(f);
			OutputStreamWriter osw=new OutputStreamWriter(fos);
			BufferedWriter bw=new BufferedWriter(osw);
			bw.write(old);
			bw.write(Text);
			bw.close();
		} else {
			FileOutputStream fos=new FileOutputStream(f);
			OutputStreamWriter osw=new OutputStreamWriter(fos);
			BufferedWriter bw=new BufferedWriter(osw);
			bw.write(Text);
			bw.close();
		}
		
	}
	public void clearAll(String filepath) throws Exception {		//把一个文件内容清空
		File f=new File(filepath);
		FileOutputStream fos=new FileOutputStream(f);
		OutputStreamWriter osw=new OutputStreamWriter(fos);
		BufferedWriter bw=new BufferedWriter(osw);
		bw.write("");
		bw.close();
	}
}