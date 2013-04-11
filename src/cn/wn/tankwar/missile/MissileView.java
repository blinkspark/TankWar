package cn.wn.tankwar.missile;

import java.awt.Graphics;
import java.awt.Graphics2D;

import cn.wn.tankwar.interfaces.View;
import cn.wn.tankwar.resource.R;

/**
 * ×Óµ¯ÊÓÍ¼
 * 
 * @author Wangning
 * 
 */
public class MissileView implements View {

	private Missile missile;
	private int currentAnimaStage = 0;

	public void attach(Missile missile) {
		this.missile = missile;
	}

	@Override
	public void draw(Graphics g) {
		if (currentAnimaStage > 10) {
			currentAnimaStage = 0;
		}

		Graphics2D g2d = (Graphics2D) g;

		switch (missile.direction) {
		case U:
			g2d.rotate(Math.toRadians(-90), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			drawMissile(g);
			g2d.rotate(Math.toRadians(90), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			break;
		case D:
			g2d.rotate(Math.toRadians(90), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			drawMissile(g);
			g2d.rotate(Math.toRadians(-90), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			break;
		case L:
			g2d.rotate(Math.toRadians(180), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			drawMissile(g);
			g2d.rotate(Math.toRadians(-180), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			break;
		case R:
			drawMissile(g);
			break;
		case LU:
			g2d.rotate(Math.toRadians(-135), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			drawMissile(g);
			g2d.rotate(Math.toRadians(135), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			break;
		case RU:
			g2d.rotate(Math.toRadians(-45), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			drawMissile(g);
			g2d.rotate(Math.toRadians(45), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			break;
		case LD:
			g2d.rotate(Math.toRadians(135), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			drawMissile(g);
			g2d.rotate(Math.toRadians(-135), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			break;
		case RD:
			g2d.rotate(Math.toRadians(45), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			drawMissile(g);
			g2d.rotate(Math.toRadians(-45), missile.getX()+missile.getWidth()/2,
					missile.getY()+missile.getHeight()/2);
			break;

		default:
			break;
		}
	}

	private void drawMissile(Graphics g) {
		if(missile.isAlive()){
			g.drawImage(R.Drawable.missileImage, missile.getX(), missile.getY(),
					missile.getX() + missile.getWidth(),
					missile.getY() + missile.getHeight(), currentAnimaStage
					* missile.getWidth(), 0,
					currentAnimaStage * missile.getWidth() + missile.getWidth(),
					missile.getHeight(), null);
			currentAnimaStage++;
		}
	}

}
