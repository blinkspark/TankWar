package cn.wn.tankwar.tank;

import cn.wn.tankwar.TankClient;

public class ShadowCloneTankController extends PlayerTankController {

	public ShadowCloneTankController(TankClient tc) {
		super(tc);
	}

	@Override
	public void shadowClone() {
		//Do Nothing
	}

	@Override
	public void shieldOn() {
		//Do Nothing
	}

	@Override
	public boolean isPlayerTank() {
		return false;
	}


}
