package cn.wn.tankwar.missile;

import cn.wn.tankwar.interfaces.Controller;

/**
 * ×Óµ¯¿ØÖÆÆ÷
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
		// TODO Auto-generated method stub
	}

}
