package cn.wn.tankwar.missile;

import java.awt.Point;

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

	public Missile(int x, int y, int width, int height,
			MissileController controller, MissileView view, Directions direction) {
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
	}
	
	public void setCenter(int x,int y) {
		centerPoint.x=x;
		centerPoint.y=y;
		switch (direction) {
		case U:
			this.x = x-width/2;
			this.y = y+height/2;
			break;
		case D:
			this.x = x+width/2;
			this.y = y-height/2;
			break;
		case L:
			this.x = x+width/2;
			this.y = y+height/2;
			break;
		case R:
			this.x = x-width/2;
			this.y = y-height/2;
			break;
		case LU:
			this.x=x;
			this.y = y+(int) Math.pow(Math.sqrt(width/2)/2, 2) ;
			break;
		case RU:
			this.x = x-(int) Math.pow(Math.sqrt(width/2)/2, 2) ;
			this.y = y;
			break;
		case LD:
			this.x = x+(int) Math.pow(Math.sqrt(width/2)/2, 2) ;
			this.y = y;
			break;
		case RD:
			this.x=x;
			this.y = y-(int) Math.pow(Math.sqrt(width/2)/2, 2) ;
			break;

		default:
			break;
		}
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
		this.centerPoint.x = this.x = x;
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
		this.centerPoint.y = this.y = y;
	}

}
