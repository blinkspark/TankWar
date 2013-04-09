package cn.wn.tankwar.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class R {
	public static class Drawable{
		
		public static BufferedImage tankImage;
		
		public static void init() {
			File tankImageFile = new File(System.getProperty("user.dir")+"\\res\\tank.png");
			try {
				tankImage = ImageIO.read(tankImageFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
