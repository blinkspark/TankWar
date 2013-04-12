package cn.wn.tankwar.missile;

import java.awt.Point;
import java.awt.Rectangle;

import cn.wn.tankwar.Directions;

/**
 * 子弹类
 * 
 * @author Wangning
 * 
 */
public class Missile {

	protected Point centerPoint;
	protected MissileController controller;
	protected int height;
	protected MissileView view;
	protected int width;
	protected int x;
	protected int y;
	protected Directions direction;
	private boolean alive;
	private boolean good;

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Missile(int x, int y, int width, int height,
			MissileController controller, MissileView view, Directions direction,boolean good) {
		this.x = x;
		this.y = y;
		this.centerPoint = new Point(x + width / 2, y + height / 2);
		this.width = width;
		this.height = height;
		this.direction = direction;

		this.controller = controller;
		this.controller.attach(this);
		this.view = view;
		this.view.attach(this);
		alive = true;
		this.good = good;
	}

	public void setCenter(int x, int y) {
		centerPoint.x = x;
		centerPoint.y = y;
		this.x = x - width / 2;
		this.y = y - height / 2;
	}

	public Directions getDirection() {
		return direction;
	}

	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	public Point getCenterPoint() {
		return centerPoint;
	}

	public MissileController getController() {
		return controller;
	}

	public void setController(MissileController controller) {
		this.controller = controller;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public MissileView getView() {
		return view;
	}

	public void setView(MissileView view) {
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

	/**
	 * 设置左上角坐标的同时设置中心坐标
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
		this.centerPoint.x = x + width / 2;
	}

	public int getY() {
		return y;
	}

	/**
	 * 设置左上角坐标的同时设置中心坐标
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
		centerPoint.y = y + height / 2;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}

	public boolean isGood() {
		return good;
	}

}
