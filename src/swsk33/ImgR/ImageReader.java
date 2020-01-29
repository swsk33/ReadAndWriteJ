package swsk33.ImgR;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
public class ImageReader {
	public int getImageWidth(String imgpath) throws Exception {			//获取图片的宽
		int width=0;
		File imgwf=new File(imgpath);
		FileInputStream fisw=new FileInputStream(imgwf);
		BufferedImage imgw=ImageIO.read(fisw);
		width=imgw.getWidth();
		fisw.close();
		return width;
	}
	public int getImageHeight(String imgpath) throws Exception {		//获取图片的高
		int height=0;
		File imghf=new File(imgpath);
		FileInputStream fish=new FileInputStream(imghf);
		BufferedImage imgh=ImageIO.read(fish);
		height=imgh.getHeight();
		fish.close();
		return height;
	}
	public ImageIcon getImg(String imgpath) { 		//获取图片对象
		ImageIcon img=new ImageIcon(imgpath);
		return img;
	}
}
