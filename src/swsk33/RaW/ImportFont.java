package swsk33.RaW;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class ImportFont {
	public Font getFont(String filepath,int wordsize){		//�����Դ������ļ��ķ���
        Font font=null;
        File file = new File(filepath);
        try{
            font=Font.createFont(Font.TRUETYPE_FONT, file);
            font=font.deriveFont(Font.PLAIN,wordsize);
        }catch (FontFormatException e){
            return null;
        }catch (FileNotFoundException e){
            return null;
        }catch (IOException e){
            return null;
        }
        return font;
    }
	public Font getBoldFont(String filepath,int wordsize){		//�����Դ������ļ��ķ���(�Ӵ���)
        Font font=null;
        File file = new File(filepath);
        try{
            font=Font.createFont(Font.TRUETYPE_FONT, file);
            font=font.deriveFont(Font.BOLD,wordsize);
        }catch (FontFormatException e){
            return null;
        }catch (FileNotFoundException e){
            return null;
        }catch (IOException e){
            return null;
        }
        return font;
    }
	public Font getItalicFont(String filepath,int wordsize){		//�����Դ������ļ��ķ���(б��)
        Font font=null;
        File file = new File(filepath);
        try{
            font=Font.createFont(Font.TRUETYPE_FONT, file);
            font=font.deriveFont(Font.ITALIC,wordsize);
        }catch (FontFormatException e){
            return null;
        }catch (FileNotFoundException e){
            return null;
        }catch (IOException e){
            return null;
        }
        return font;
    }
	public Font getItalicBoldFont(String filepath,int wordsize){		//�����Դ������ļ��ķ���(б�Ӵ���)
        Font font=null;
        File file = new File(filepath);
        try{
            font=Font.createFont(Font.TRUETYPE_FONT, file);
            font=font.deriveFont(Font.ITALIC|Font.BOLD,wordsize);
        }catch (FontFormatException e){
            return null;
        }catch (FileNotFoundException e){
            return null;
        }catch (IOException e){
            return null;
        }
        return font;
    }
}
