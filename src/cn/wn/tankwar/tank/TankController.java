package cn.wn.tankwar.tank;

import cn.wn.tankwar.Directions;
import cn.wn.tankwar.interfaces.Controller;

/**
 * 坦克控制器
 * 
 * @author Wangning
 * 
 */
public class TankController implements Controller {
	private Tank tank;

	/**
	 * 移动方法,并在移动后设置坦克方向
	 */
	public void move() {
		if (tank.upPressed) {
			tank.getController().moveUp();
			tank.setDirection(Directions.U);
		}
		if (tank.downPressed) {
			tank.getController().moveDown();
			tank.setDirection(Directions.D);
		}
		if (tank.leftPressed) {
			tank.getController().moveLeft();
			if(tank.upPressed){
				tank.setDirection(Directions.LU);
			}else if(tank.downPressed){
				tank.setDirection(Directions.LD);
			}else {
				tank.setDirection(Directions.L);
			}
		}
		if (tank.rightPressed) {
			tank.getController().moveRight();
			if(tank.upPressed){
				tank.setDirection(Directions.RU);
			}else if(tank.downPressed){
				tank.setDirection(Directions.RD);
			}else {
				tank.setDirection(Directions.R);
			}
		}
	}

	/**
	 * 获取绑定的对象
	 * @return
	 */
	public Tank getTank() {
		return tank;
	}

	/**
	 * 绑定坦克对象
	 * @param tank
	 */
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
