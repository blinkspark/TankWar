package cn.wn.tankwar.tank;

import java.awt.Image;
import java.awt.Point;

/**
 * 坦克模型类
 * @author Wangning
 *
 */
public class Tank {

	protected Point centerPoint;

	protected TankController controller;
	protected int height;
	protected TankView view;
	protected int width;
	protected int x;
	protected int y;
	public boolean isUpPressed() {
		return upPressed;
	}

	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	protected boolean upPressed = false;
	protected boolean downPressed = false;
	protected boolean leftPressed = false;
	protected boolean rightPressed = false;

	protected Image image;

	/**
	 * 坦克的构造方法
	 * @param x 左上角x坐标
	 * @param y 左上角y坐标
	 * @param width 宽度
	 * @param height 高度
	 * @param controller 控制器
	 * @param view 视图
	 */
	public Tank(int x, int y, int width, int height,Image tankImage, TankController controller,
			TankView view) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image=tankImage;
		this.controller = controller;
		this.view = view;
		this.controller = controller;
		this.centerPoint = new Point(x+width/2,y+height/2);
		this.view.attach(this);
		this.controller.attach(this);
	}

	public Point getCenterPoint() {
		return centerPoint;
	}

	/**
	 * 获取坦克的控制器
	 * @return TankController
	 */
	public TankController getController() {
		return controller;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * 获取坦克视图类的方法
	 * @return TankView
	 */
	public TankView getView() {
		return view;
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setCenterPoint(Point centerPoint) {
		this.centerPoint = centerPoint;
	}

	/**
	 * 设置坦克的控制器
	 * @param controller TankController
	 */
	public void setController(TankController controller) {
		this.controller = controller;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * 设置坦克的视图类
	 * @param view 坦克视图类
	 */
	public void setView(TankView view) {
		this.view = view;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

}
