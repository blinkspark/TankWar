package cn.wn.tankwar.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 资源管理类,所以的资源都使用该类进行加载
 * @author Wangning
 *
 */
public class R {
	public static class Drawable{
		
		public static BufferedImage tankImage;
		public static BufferedImage missileImage;
		
		/**
		 * 初始化方法,初始化资源类,要再程序的一开始调用
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
