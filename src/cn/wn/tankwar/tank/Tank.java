package cn.wn.tankwar.tank;

import java.awt.Point;
import java.awt.Rectangle;

import cn.wn.tankwar.Directions;

/**
 * 坦克模型类
 * 
 * @author Wangning
 * 
 */
public class Tank {

	protected Point centerPoint;

	protected PlayerTankController controller;
	protected Directions direction;
	protected boolean downPressed = false;
	protected int height;
	protected boolean leftPressed = false;
	protected boolean rightPressed = false;
	protected boolean upPressed = false;
	protected boolean good;
	protected boolean alive;

	protected PlayerTankView view;

	protected int width;

	protected int x;

	protected int y;

	/**
	 * 坦克的构造方法
	 * 
	 * @param x
	 *            左上角x坐标
	 * @param y
	 *            左上角y坐标
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param controller
	 *            控制器
	 * @param view
	 *            视图
	 */
	public Tank(int x, int y, int width, int height, PlayerTankController controller,
			PlayerTankView view) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.direction = Directions.U;

		this.controller = controller;
		this.view = view;
		this.controller = controller;
		this.centerPoint = new Point(x + width / 2, y + height / 2);
		this.view.attach(this);
		this.controller.attach(this);
		good = true;
		alive = true;
	}

	/**
	 * 获得坦克的中心点
	 * 
	 * @return 返回Point类
	 */
	public Point getCenterPoint() {
		return centerPoint;
	}

	/**
	 * 获取坦克的控制器
	 * 
	 * @return TankController
	 */
	public PlayerTankController getController() {
		return controller;
	}

	/**
	 * 获得坦克的方向
	 * 
	 * @return
	 */
	public Directions getDirection() {
		return direction;
	}

	/**
	 * 获得坦克的高度
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 获取坦克视图类的方法
	 * 
	 * @return TankView
	 */
	public PlayerTankView getView() {
		return view;
	}

	/**
	 * 获得坦克的高度
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 获得坦克左上角的X坐标
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * 获得坦克左上角的Y坐标
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * 返回下箭头键是否按下
	 * 
	 * @return
	 */
	public boolean isDownPressed() {
		return downPressed;
	}

	/**
	 * 返回左箭头键是否按下
	 * 
	 * @return
	 */
	public boolean isLeftPressed() {
		return leftPressed;
	}

	/**
	 * 返回右箭头是否按下
	 * 
	 * @return
	 */
	public boolean isRightPressed() {
		return rightPressed;
	}

	/**
	 * 返回上箭头是否按下
	 * 
	 * @return
	 */
	public boolean isUpPressed() {
		return upPressed;
	}

	/**
	 * 设置坦克的控制器
	 * 
	 * @param controller
	 *            TankController
	 */
	public void setController(PlayerTankController controller) {
		this.controller = controller;
	}

	/**
	 * 设置方向
	 * 
	 * @param direction
	 */
	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	/**
	 * 设置下按下状态
	 * 
	 * @param downPressed
	 */
	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}

	/**
	 * 设置坦克的大小
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 设置左箭头按下状态
	 * 
	 * @param leftPressed
	 */
	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	/**
	 * 设置右箭头按下状态
	 * 
	 * @param rightPressed
	 */
	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	/**
	 * 设置上箭头按下状态
	 * 
	 * @param upPressed
	 */
	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	/**
	 * 设置坦克的视图类
	 * 
	 * @param view
	 *            坦克视图
	 */
	public void setView(PlayerTankView view) {
		this.view = view;
	}

	/**
	 * 设置坦克的宽度
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 设置坦克左上角X坐标,同时更新中心点X坐标
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
		updateCenter();
	}

	/**
	 * 设置坦克左上角Y坐标,同时更新中心点Y坐标
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
		updateCenter();
	}

	public void updateCenter() {
			centerPoint.x = x + width / 2;
			centerPoint.y = y + height / 2;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}

	public boolean isGood() {
		return good;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
