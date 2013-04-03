package cn.wn.tankwar.tank;

import java.awt.Point;

/**
 * 坦克模型类
 * @author Wangning
 *
 */
public class Tank {

	/**
	 * 坦克的构造方法
	 * @param x 左上角x坐标
	 * @param y 左上角y坐标
	 * @param width 宽度
	 * @param height 高度
	 * @param controller 控制器
	 * @param view 视图
	 */
	public Tank(int x, int y, int width, int height, TankController controller,
			TankView view) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.controller = controller;
		this.view = view;
		this.centerPoint = new Point(x+width/2,y+height/2);
		view.attach(this);
	}

	protected int x;
	protected int y;
	protected Point centerPoint;
	protected int width;
	protected int height;
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

	public Point getCenterPoint() {
		return centerPoint;
	}

	public void setCenterPoint(Point centerPoint) {
		this.centerPoint = centerPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	protected TankController controller;
	protected TankView view;
	
	/**
	 * 获取坦克视图类的方法
	 * @return TankView
	 */
	public TankView getView() {
		return view;
	}

	/**
	 * 设置坦克的视图类
	 * @param view 坦克视图类
	 */
	public void setView(TankView view) {
		this.view = view;
	}

	/**
	 * 设置坦克的控制器
	 * @param controller TankController
	 */
	public void setController(TankController controller) {
		this.controller = controller;
	}
	
	/**
	 * 获取坦克的控制器
	 * @return TankController
	 */
	public TankController getController() {
		return controller;
	}

}
