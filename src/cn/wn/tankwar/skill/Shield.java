package cn.wn.tankwar.skill;

import java.util.Date;

import cn.wn.tankwar.tank.Tank;

public class Shield {
	private static final int COOL_DOWN_S = 2;
	private Tank tank;
	private boolean on;
	private Date coolDownDate;
	private ShieldView view;

	public Shield(Tank tank) {
		this.tank = tank;
		on = false;
		this.coolDownDate = new Date();
		this.view = new ShieldView(this);
	}

	public Tank getTank() {
		return tank;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		if (on) {
			Date curDate = new Date();
			if (curDate.after(coolDownDate)) {
				this.on = on;
			}
		} else {
			this.on = on;
			coolDownDate.setTime(new Date().getTime() + COOL_DOWN_S * 1000);
		}
	}

	public ShieldView getView() {
		return view;
	}
}
