package cn.wn.tankwar.tank;

import java.util.Random;

import cn.wn.tankwar.Directions;
import cn.wn.tankwar.TankClient;

public class EnemyTankController extends PlayerTankController {

	private Random random = new Random();
	protected static final int SPEED = 2;

	public EnemyTankController(TankClient tc) {
		super(tc);
	}

	private Tank targeTank;

	@Override
	public void move() {
		// TODO
		initTarget();
		if (targeTank == null) {
			return;
		}
		getTargetDirection();
		if (tank.upPressed) {
			tank.getController().moveUp(SPEED);
		}
		if (tank.downPressed) {
			tank.getController().moveDown(SPEED);
		}
		if (tank.leftPressed) {
			tank.getController().moveLeft(SPEED);
		}
		if (tank.rightPressed) {
			tank.getController().moveRight(SPEED);
		}
		if (isObstruct()) {
			unMove();
		}
		if (random.nextInt(1000) > 990) {
			fire();
		}
		targeTank = null;
	}

	private Directions getTargetDirection() {
		if (targeTank.getCenterPoint().x - tank.getCenterPoint().x > tank
				.getWidth()) {
			tank.setRightPressed(true);
			tank.setLeftPressed(false);
		} else if (targeTank.getCenterPoint().x - tank.getCenterPoint().x <= tank
				.getWidth()
				&& targeTank.getCenterPoint().x - tank.getCenterPoint().x >= -tank
						.getWidth()) {
			tank.setLeftPressed(false);
			tank.setRightPressed(false);
		} else {
			tank.setRightPressed(false);
			tank.setLeftPressed(true);
		}

		if (targeTank.getCenterPoint().y - tank.getCenterPoint().y > tank
				.getHeight()) {
			tank.setDownPressed(true);
			tank.setUpPressed(false);
		} else if (targeTank.getCenterPoint().y - tank.getCenterPoint().y <= tank
				.getHeight()
				&& targeTank.getCenterPoint().y - tank.getCenterPoint().y >= -tank
						.getHeight()) {
			tank.setUpPressed(false);
			tank.setDownPressed(false);
		} else {
			tank.setUpPressed(true);
			tank.setDownPressed(false);
		}

		if (tank.isUpPressed()) {
			tank.setDirection(Directions.U);
		}
		if (tank.isDownPressed()) {
			tank.setDirection(Directions.D);
		}
		if (tank.isLeftPressed()) {
			tank.setDirection(Directions.L);
			if (tank.isUpPressed()) {
				tank.setDirection(Directions.LU);
			}
			if (tank.isDownPressed()) {
				tank.setDirection(Directions.LD);
			}
		}
		if (tank.isRightPressed()) {
			tank.setDirection(Directions.R);
			if (tank.isUpPressed()) {
				tank.setDirection(Directions.RU);
			}
			if (tank.isDownPressed()) {
				tank.setDirection(Directions.RD);
			}
		}
		return tank.direction;

	}

	private void initTarget() {
		for (Tank tank : tc.getTanks()) {
			if (tank.isGood()) {
				targeTank = tank;
			}
		}
	}

}
