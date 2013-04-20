package cn.wn.tankwar.skill;

import java.awt.Graphics;

import cn.wn.tankwar.interfaces.View;
import cn.wn.tankwar.resource.R;

public class ShieldView implements View {

	private Shield shield;
public ShieldView(Shield shield) {
	this.shield = shield;
}
	
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(R.Drawable.shieldImage, shield.getTank().getX(), shield
				.getTank().getY(), null);
	}

	@Override
	public void attach(Object object) {
		attach((Shield) object);
	}

	public void attach(Shield shield) {
		this.shield = shield;
	}

}
