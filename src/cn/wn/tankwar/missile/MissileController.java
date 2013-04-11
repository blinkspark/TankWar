package cn.wn.tankwar.missile;

import cn.wn.tankwar.TankClient;
import cn.wn.tankwar.explode.Explode;
import cn.wn.tankwar.explode.ExplodeView;
import cn.wn.tankwar.interfaces.Controller;

/**
 * �ӵ�������
 * 
 * @author Wangning
 * 
 */
public class MissileController implements Controller {

	private Missile missile;
	private TankClient tc;

	public MissileController(TankClient tc) {
		this.tc = tc;
	}

	public void attach(Missile missile) {
		this.missile = missile;
	}

	@Override
	public void move() {
		if (isHit()) {
			tc.explode = new Explode(0, 0, 56, 56, new ExplodeView());
			tc.explode.setCenter(missile.getCenterPoint().x, missile.getCenterPoint().y);
			missile.setAlive(false);
			return;
		}
		switch (missile.getDirection()) {
		case U:
			moveUp();
			break;
		case D:
			moveDown();
			break;
		case L:
			moveLeft();
			break;
		case R:
			moveRight();
			break;
		case LU:
			moveUp();
			moveLeft();
			break;
		case RU:
			moveUp();
			moveRight();
			break;
		case LD:
			moveDown();
			moveLeft();
			break;
		case RD:
			moveDown();
			moveRight();
			break;

		default:
			break;
		}
	}

	private boolean isHit() {
		boolean hit = false;
		if (missile.getRect().intersects(tc.getObtacle().getRect())) {
			hit = true;
		}
		return hit;
	}

	private void moveRight() {
		missile.setX(missile.getX() + 1);
	}

	private void moveLeft() {
		missile.setX(missile.getX() - 1);
	}

	private void moveDown() {
		missile.setY(missile.getY() + 1);
	}

	private void moveUp() {
		missile.setY(missile.getY() - 1);
	}

}
