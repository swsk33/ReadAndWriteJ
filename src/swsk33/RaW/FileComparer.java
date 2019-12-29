package swsk33.RaW;
import java.io.*;
public class FileComparer {
	public boolean comFile(String File1,String File2) throws IOException {		//比较两个文件是否完全一致
		File f1=new File(File1);
		File f2=new File(File2);
		boolean result=false;
		BufferedReader reader1=new BufferedReader(new FileReader(File1));
	    StringBuilder results1=new StringBuilder();
	    String theLine1=null;
	    while(( theLine1=reader1.readLine())!=null){
	    	results1.append(theLine1+"\n");
	    }
	    BufferedReader reader2=new BufferedReader(new FileReader(File2));
	    StringBuilder results2=new StringBuilder();
	    String theLine2=null;
	    while(( theLine2=reader2.readLine())!=null){
	    	results2.append(theLine2+"\n");
	    }
	    String s1=results1.toString();
	    String s2=results2.toString();
	    if(s1.equals(s2)) {
	    	result=true;
	    }
		return result;
	}
}
