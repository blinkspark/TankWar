package cn.wn.tankwar.missile;

import java.awt.Graphics;

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
		if(currentAnimaStage>10){
			currentAnimaStage = 0;
		}
		g.drawImage(R.Drawable.missileImage, missile.getX(), missile.getY(),
				missile.getX() + missile.getWidth(),
				missile.getY() + missile.getHeight(), currentAnimaStage*missile.getWidth(), 0, currentAnimaStage*missile.getWidth()+missile.getWidth(),
				missile.getHeight(), null);
		currentAnimaStage++;
	}

}
