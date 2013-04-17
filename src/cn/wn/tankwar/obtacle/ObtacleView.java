package cn.wn.tankwar.obtacle;

import java.awt.Graphics;

import cn.wn.tankwar.interfaces.View;
import cn.wn.tankwar.resource.R;

public class ObtacleView implements View{
	
	private Obtacle obtacle;

	public void attach(Obtacle obtacle) {
		this.obtacle = obtacle;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(R.Drawable.obtacleImage, obtacle.getX(), obtacle.getY(), null);	
		g.drawImage(R.Drawable.shieldImage, 70, 70, null);
	}

	@Override
	public void attach(Object object) {
		attach((Obtacle)object);
		
	}


}
