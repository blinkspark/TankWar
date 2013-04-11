package cn.wn.tankwar.obtacle;

public class Obtacle {
	protected int height;
	protected ObtacleView view;
	protected int width;
	protected int x;
	protected int y;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ObtacleView getView() {
		return view;
	}

	public void setView(ObtacleView view) {
		this.view = view;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Obtacle(int x, int y, int height, int width, ObtacleView view) {
		super();
		this.height = height;
		this.view = view;
		this.view.attach(this);
		this.width = width;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
