package cn.wn.tankwar.explode;

import java.awt.Point;

public class Explode {
	protected Point centerPoint;
	protected int height;
	protected ExplodeView view;
	protected int width;
	protected int x;
	protected int y;
	private boolean alive;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ExplodeView getView() {
		return view;
	}

	public void setView(ExplodeView view) {
		this.view = view;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		this.centerPoint.x = this.x + width / 2;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.centerPoint.y = this.y + height / 2;
	}

	public void setCenter(int x, int y) {
		centerPoint.x = x;
		centerPoint.y = y;
		this.x = x - width / 2;
		this.y = y - height / 2;
	}

	public Explode(int x, int y, int width, int height, ExplodeView view) {
		this.x = x;
		this.y = y;
		centerPoint = new Point();
		centerPoint.x = x + width / 2;
		centerPoint.y = y + height / 2;
		this.width = width;
		this.height = height;
		this.view = view;
		this.view.attach(this);
		alive = true;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
