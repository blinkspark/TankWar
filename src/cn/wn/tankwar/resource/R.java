package cn.wn.tankwar.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 资源管理类,所以的资源都使用该类进行加载
 * 
 * @author Wangning
 * 
 */
public class R {
	public static class Drawable {

		public static BufferedImage playerTank3Image;
		public static BufferedImage playerTank2Image;
		public static BufferedImage playerTank1Image;
		public static BufferedImage playerTankDeadImage;
		public static BufferedImage missileImage;
		public static BufferedImage backgroundImage;
		public static BufferedImage obtacleImage;
		public static BufferedImage explodeImage;
		public static BufferedImage enemyTankImage;

		/**
		 * 初始化方法,初始化资源类,要在程序的一开始调用
		 */
		public static void init() {
			File playerTankImage3File = new File(System.getProperty("user.dir")
					+ "\\res\\player_tank_3.png");
			File playerTankImage2File = new File(System.getProperty("user.dir")
					+ "\\res\\player_tank_2.png");
			File playerTankImage1File = new File(System.getProperty("user.dir")
					+ "\\res\\player_tank_1.png");
			File playerTankImageDeadFile = new File(System.getProperty("user.dir")
					+ "\\res\\player_tank_dead.png");
			File enemyTankImageFile = new File(System.getProperty("user.dir")
					+ "\\res\\enemy_tank.png");
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
				playerTank3Image = ImageIO.read(playerTankImage3File);
				playerTank2Image = ImageIO.read(playerTankImage2File);
				playerTank1Image = ImageIO.read(playerTankImage1File);
				playerTankDeadImage = ImageIO.read(playerTankImageDeadFile);
				enemyTankImage = ImageIO.read(enemyTankImageFile);
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
