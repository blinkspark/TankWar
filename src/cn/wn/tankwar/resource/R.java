package cn.wn.tankwar.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ��Դ������,���Ե���Դ��ʹ�ø�����м���
 * 
 * @author Wangning
 * 
 */
public class R {
	public static class Drawable {

		public static BufferedImage tankImage;
		public static BufferedImage missileImage;
		public static BufferedImage backgroundImage;
		public static BufferedImage obtacleImage;
		public static BufferedImage explodeImage;

		/**
		 * ��ʼ������,��ʼ����Դ��,Ҫ�ڳ����һ��ʼ����
		 */
		public static void init() {
			File tankImageFile = new File(System.getProperty("user.dir")
					+ "\\res\\tank.png");
			File missileImageFile = new File(System.getProperty("user.dir")
					+ "\\res\\missile.png");
			File obtacleImageFile = new File(System.getProperty("user.dir")
					+ "\\res\\obtacle.png");
			File explodeImageFile = new File(System.getProperty("user.dir")
					+ "\\res\\explode.png");
			File backgroundImageFile = new File(System.getProperty("user.dir")
					+ "\\res\\background.jpg");
			try {
				backgroundImage = ImageIO.read(backgroundImageFile);
				tankImage = ImageIO.read(tankImageFile);
				obtacleImage = ImageIO.read(obtacleImageFile);
				explodeImage = ImageIO.read(explodeImageFile);
				missileImage = ImageIO.read(missileImageFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
