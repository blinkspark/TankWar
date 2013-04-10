package cn.wn.tankwar.missile;

import cn.wn.tankwar.interfaces.Controller;

/**
 * ×Óµ¯¿ØÖÆÆ÷
 * 
 * @author Wangning
 * 
 */
public class MissileController implements Controller {

	private Missile missile;

	public void attach(Missile missile) {
		this.missile = missile;
	}

	@Override
	public void move() {
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
