package cn.wn.tankwar.tank;

import cn.wn.tankwar.Directions;
import cn.wn.tankwar.TankClient;
import cn.wn.tankwar.interfaces.Controller;
import cn.wn.tankwar.missile.Missile;
import cn.wn.tankwar.missile.MissileController;
import cn.wn.tankwar.missile.MissileView;

/**
 * ̹�˿�����
 * 
 * @author Wangning
 * 
 */
public class TankController implements Controller {
	private Tank tank;

	/**
	 * �ƶ�����,�����ƶ�������̹�˷���
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
			if (tank.upPressed) {
				tank.setDirection(Directions.LU);
			} else if (tank.downPressed) {
				tank.setDirection(Directions.LD);
			} else {
				tank.setDirection(Directions.L);
			}
		}
		if (tank.rightPressed) {
			tank.getController().moveRight();
			if (tank.upPressed) {
				tank.setDirection(Directions.RU);
			} else if (tank.downPressed) {
				tank.setDirection(Directions.RD);
			} else {
				tank.setDirection(Directions.R);
			}
		}
	}

	/**
	 * ��ȡ�󶨵Ķ���
	 * 
	 * @return
	 */
	public Tank getTank() {
		return tank;
	}

	/**
	 * ��̹�˶���
	 * 
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

	public void fire(TankClient tankClient) {
		tankClient.missile = new Missile(tank.getX(), tank.getY(), 40, 40,
				new MissileController(), new MissileView(), tank.getDirection());
		tankClient.missile.setCenter(tank.getCenterPoint().x, tank.getCenterPoint().y);

	}

}
