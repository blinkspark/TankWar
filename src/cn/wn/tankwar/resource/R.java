package cn.wn.tankwar.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ��Դ������,���Ե���Դ��ʹ�ø�����м���
 * @author Wangning
 *
 */
public class R {
	public static class Drawable{
		
		public static BufferedImage tankImage;
		public static BufferedImage missileImage;
		
		/**
		 * ��ʼ������,��ʼ����Դ��,Ҫ�ٳ����һ��ʼ����
		 */
		public static void init() {
			File tankImageFile = new File(System.getProperty("user.dir")+"\\res\\tank.png");
			File missileImageFile = new File(System.getProperty("user.dir")+"\\res\\missile.png");
			try {
				tankImage = ImageIO.read(tankImageFile);
				missileImage = ImageIO.read(missileImageFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
