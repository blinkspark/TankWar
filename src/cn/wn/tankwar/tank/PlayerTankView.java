package cn.wn.tankwar.tank;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import cn.wn.tankwar.interfaces.View;
import cn.wn.tankwar.resource.R;

/**
 * 坦克试图
 * 
 * @author Wangning
 * 
 */
public class PlayerTankView implements View {

	private Tank tank;

	/**
	 * 绑定对象
	 * 
	 * @param tank
	 */
	public void attach(Tank tank) {
		this.tank = tank;
	}

	/**
	 * 绘图方法
	 */
	@Override
	public void draw(Graphics g) {
		Image image = null;
		switch (tank.getHealth()) {
		case 3:
			image = R.Drawable.playerTank3Image;
			break;
		case 2:
			image = R.Drawable.playerTank2Image;
			break;
		case 1:
			image = R.Drawable.playerTank1Image;
			break;
		case 0:
			image = R.Drawable.playerTankDeadImage;
			break;

		default:
			break;
		}
		Graphics2D g2d = (Graphics2D) g;
		switch (tank.getDirection()) {
		case U:
			g.drawImage(image, tank.getX(), tank.getY(), null);
			break;
		case D:
			g2d.rotate(Math.toRadians(180), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(image, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(-180), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case L:
			g2d.rotate(Math.toRadians(-90), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(image, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(90), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case R:
			g2d.rotate(Math.toRadians(90), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(image, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(-90), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case LU:
			g2d.rotate(Math.toRadians(-45), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(image, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(45), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case RU:
			g2d.rotate(Math.toRadians(45), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(image, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(-45), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case LD:
			g2d.rotate(Math.toRadians(-135), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(image, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(135), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;
		case RD:
			g2d.rotate(Math.toRadians(135), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			g.drawImage(image, tank.getX(), tank.getY(), null);
			g2d.rotate(Math.toRadians(-135), tank.getX() + tank.getWidth() / 2,
					tank.getY() + tank.getHeight() / 2);
			break;

		default:
			break;
		}
		
		if (tank.getShield().isOn()) {
			tank.getShield().getView().draw(g);
		}
		
	}

	@Override
	public void attach(Object object) {
		attach((Tank)object);
		
	}

}
