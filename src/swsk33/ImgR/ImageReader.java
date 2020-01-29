package swsk33.ImgR;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
public class ImageReader {
	public int getImageWidth(String imgpath) throws Exception {			//��ȡͼƬ�Ŀ�
		int width=0;
		File imgwf=new File(imgpath);
		FileInputStream fisw=new FileInputStream(imgwf);
		BufferedImage imgw=ImageIO.read(fisw);
		width=imgw.getWidth();
		fisw.close();
		return width;
	}
	public int getImageHeight(String imgpath) throws Exception {		//��ȡͼƬ�ĸ�
		int height=0;
		File imghf=new File(imgpath);
		FileInputStream fish=new FileInputStream(imghf);
		BufferedImage imgh=ImageIO.read(fish);
		height=imgh.getHeight();
		fish.close();
		return height;
	}
	public ImageIcon getImg(String imgpath) { 		//��ȡͼƬ����
		ImageIcon img=new ImageIcon(imgpath);
		return img;
	}
}
