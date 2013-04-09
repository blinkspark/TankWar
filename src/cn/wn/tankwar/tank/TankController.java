package cn.wn.tankwar.tank;

import cn.wn.tankwar.interfaces.Controller;

/**
 * Ì¹¿Ë¿ØÖÆÆ÷
 * 
 * @author Wangning
 * 
 */
public class TankController implements Controller {
	private Tank tank;
	
	public void move() {
		if (tank.upPressed)
			tank.getController().moveUp();
		if (tank.downPressed)
			tank.getController().moveDown();
		if (tank.leftPressed)
			tank.getController().moveLeft();
		if (tank.rightPressed)
			tank.getController().moveRight();
	}

	public Tank getTank() {
		return tank;
	}

	public void attach(Tank tank) {
		this.tank = tank;
	}

	public void moveUp() {
		tank.setY(tank.getY() - 5);
	}

	public void moveDown() {
		tank.setY(tank.getY() + 5);
	}

	public void moveLeft() {
		tank.setX(tank.getX() - 5);
	}

	public void moveRight() {
		tank.setX(tank.getX() + 5);
	}
	// public void moveRightUp() {
	//
	// }
	// public void moveLeftUp() {
	//
	// }
	// public void moveRightDown() {
	//
	// }
	// public void moveLeftDown() {
	//
	// }
}
