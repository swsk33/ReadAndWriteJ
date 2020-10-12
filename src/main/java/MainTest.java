import swsk33.RaW.*;

public class MainTest {

	private static String PATH = "D:\\test.txt";
	
	public static void main(String[] args) throws Exception {
		TextFileWriter tfw = new TextFileWriter();
		System.out.println(tfw.removeLine(PATH, 1));
	}

}
