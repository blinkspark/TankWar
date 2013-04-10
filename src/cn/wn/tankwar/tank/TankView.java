package cn.wn.tankwar.tank;

import java.awt.Graphics;
import java.awt.Graphics2D;

import cn.wn.tankwar.interfaces.View;
import cn.wn.tankwar.resource.R;

/**
 * ̹����ͼ
 * 
 * @author Wangning
 * 
 */
public class TankView implements View {

	private Tank tank;

	/**
	 * �󶨶���
	 * 
	 * @param tank
	 */
	public void attach(Tank tank) {
		this.tank = tank;
	}

	/**
	 * ��ͼ����
	 */
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		switch (tank.getDirection()) {
		case U:
			g.drawImage(R.Drawable.tankImage, tank.getX(), tank.getY(), null);
			break;
		case D:
			g2d.rotate(Math.toRadians(180), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(R.Drawable.tankImage, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(-180), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case L:
			g2d.rotate(Math.toRadians(-90), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(R.Drawable.tankImage, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(90), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case R:
			g2d.rotate(Math.toRadians(90), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(R.Drawable.tankImage, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(-90), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case LU:
			g2d.rotate(Math.toRadians(-45), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(R.Drawable.tankImage, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(45), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case RU:
			g2d.rotate(Math.toRadians(45), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(R.Drawable.tankImage, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(-45), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case LD:
			g2d.rotate(Math.toRadians(-135), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(R.Drawable.tankImage, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(135), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case RD:
			g2d.rotate(Math.toRadians(135), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(R.Drawable.tankImage, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(-135), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;

		default:
			break;
		}
	}

}
