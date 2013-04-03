package cn.wn.tankwar.tank;

import java.awt.Graphics;

import cn.wn.tankwar.interfaces.View;

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
		g.fillOval(tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());		
	}

	
}
