package cn.wn.tankwar.tank;

import java.awt.Graphics;

import cn.wn.tankwar.interfaces.View;
import cn.wn.tankwar.resource.R;

/**
 * ÃπøÀ ‘Õº
 * 
 * @author Wangning
 * 
 */
public class TankView implements View {

	private Tank tank;

	public void attach(Tank tank) {
		this.tank = tank;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(R.Drawable.tankImage, tank.getX(), tank.getY(), null);
	}

	
}
