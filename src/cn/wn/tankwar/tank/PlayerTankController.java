package cn.wn.tankwar.tank;

import cn.wn.tankwar.Directions;
import cn.wn.tankwar.TankClient;
import cn.wn.tankwar.explode.Explode;
import cn.wn.tankwar.explode.ExplodeView;
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
public class PlayerTankController implements Controller {
	protected static final int SPEED = 4;
	protected Tank tank;
	protected TankClient tc;
	

	public PlayerTankController(TankClient tc) {
		this.tc = tc;
	}

	/**
	 * �ƶ�����,�����ƶ�������̹�˷���
	 */
	public void move() {
		if (tank.upPressed) {
			moveUp(SPEED);
			tank.setDirection(Directions.U);
		}
		if (tank.downPressed) {
			moveDown(SPEED);
			tank.setDirection(Directions.D);
		}
		if (tank.leftPressed) {
			moveLeft(SPEED);
			if (tank.upPressed) {
				tank.setDirection(Directions.LU);
			} else if (tank.downPressed) {
				tank.setDirection(Directions.LD);
			} else {
				tank.setDirection(Directions.L);
			}
		}
		if (tank.rightPressed) {
			moveRight(SPEED);
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
	protected void unMove() {
		if (tank.isUpPressed()) {
			moveDown(SPEED);
		}
		if (tank.isDownPressed()) {
			moveUp(SPEED);
		}
		if (tank.isLeftPressed()) {
			moveRight(SPEED);
		}
		if (tank.isRightPressed()) {
			moveLeft(SPEED);
		}
	}

	protected boolean isObstruct() {
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

	public void moveUp(int speed) {
		tank.setY(tank.getY() - speed);
	}

	public void moveDown(int speed) {
		tank.setY(tank.getY() + speed);
	}

	public void moveLeft(int speed) {
		tank.setX(tank.getX() - speed);
	}

	public void moveRight(int speed) {
		tank.setX(tank.getX() + speed);
	}

	public void fire() {
		Missile missile = new Missile(tank.getX(), tank.getY(), 40, 40,
				new MissileController(tc), new MissileView(),
				tank.getDirection(),tank.isGood());
		missile.setCenter(tank.getCenterPoint().x, tank.getCenterPoint().y);
		tc.missiles.add(missile);

	}

	public void beingHit() {
		tank.beHited();
		if(tank.getHealth()<=0){
			tank.setAlive(false);
			Explode explode = new Explode(0, 0, 56, 56,
					new ExplodeView());
			explode.setCenter(tank.getCenterPoint().x,
					tank.getCenterPoint().y);
			tc.explodes.add(explode);
		}
	}

	public void shieldOn() {
		tank.getShield().setOn(true);
	}

	public void shadowClone() {
		synchronized (tc.getTanks()) {
			tc.getTanks().add(new Tank(tank.getX()-tank.getWidth(), tank.getY(), tank.getWidth(), tank.getHeight(), new ShadowCloneTankController(tc), new PlayerTankView(), true, 1));
			tc.getTanks().add(new Tank(tank.getX()+tank.getWidth(), tank.getY(), tank.getWidth(), tank.getHeight(), new ShadowCloneTankController(tc), new PlayerTankView(), true, 1));
			tc.getTanks().add(new Tank(tank.getX(), tank.getY()+tank.getHeight(), tank.getWidth(), tank.getHeight(), new ShadowCloneTankController(tc), new PlayerTankView(), true, 1));
			tc.getTanks().add(new Tank(tank.getX(), tank.getY()-tank.getHeight(), tank.getWidth(), tank.getHeight(), new ShadowCloneTankController(tc), new PlayerTankView(), true, 1));
		}
	}

}
