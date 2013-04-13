package cn.wn.tankwar.tank;

import java.util.Random;

import cn.wn.tankwar.Directions;
import cn.wn.tankwar.TankClient;

public class EnemyTankController extends PlayerTankController {
	
	private Random random = new Random();

	public EnemyTankController(TankClient tc) {
		super(tc);
	}

	@Override
	public void move() {
		//TODO
		if(random.nextInt(1000)>990){
			fire();
		}
		if(random.nextInt(1000)>900){
			moveDown();
			tank.setDirection(Directions.D);
		}
	}


}
