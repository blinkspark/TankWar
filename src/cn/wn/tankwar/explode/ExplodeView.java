package cn.wn.tankwar.explode;

import java.awt.Graphics;

import cn.wn.tankwar.interfaces.View;
import cn.wn.tankwar.resource.R;

public class ExplodeView implements View {

	private int currentAnimaStage = 0;
	private Explode explode;

	public void attach(Explode explode) {
		this.explode = explode;
	}

	@Override
	public void draw(Graphics g) {
		if(explode.isAlive()){
			if (currentAnimaStage > 10) {
				explode.setAlive(false);
				return;
			}
			g.drawImage(R.Drawable.explodeImage, explode.getX(), explode.getY(),
					explode.getX() + explode.getWidth(),
					explode.getY() + explode.getHeight(), currentAnimaStage
					* explode.getWidth(), 0,
					currentAnimaStage * explode.getWidth() + explode.getWidth(),
					explode.getWidth(), null);
			currentAnimaStage++;
		}
	}

	@Override
	public void attach(Object object) {
		attach((Explode)object);
	}


}
