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
	private TankClient tc;

	public TankController(TankClient tc) {
		this.tc = tc;
	}

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
		if (isObstruct()) {
			unMove();
		}
	}

	/**
	 * ��⵽��ײ��ȡ���ƶ�����
	 */
	private void unMove() {
		if (tank.isUpPressed()) {
			moveDown();
		}
		if (tank.isDownPressed()) {
			moveUp();
		}
		if (tank.isLeftPressed()) {
			moveRight();
		}
		if (tank.isRightPressed()) {
			moveLeft();
		}
	}

	private boolean isObstruct() {
		boolean obstruct = false;
		if (tank.getRect().intersects(tc.getObtacle().getRect())) {
			obstruct = true;
		} else if (tank.getX() < 0
				|| tank.getX() > TankClient.SCR_WIDTH - tank.width) {
			obstruct = true;
		} else if (tank.getY() < 0
				|| tank.getY() > TankClient.SCR_HEIGHT - tank.height) {
			obstruct = true;
		}
		return obstruct;
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

	public void fire() {
		tc.missile = new Missile(tank.getX(), tank.getY(), 40, 40,
				new MissileController(tc), new MissileView(),
				tank.getDirection());
		tc.missile.setCenter(tank.getCenterPoint().x, tank.getCenterPoint().y);

	}

}
