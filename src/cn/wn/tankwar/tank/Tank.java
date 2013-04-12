package cn.wn.tankwar.tank;

import java.awt.Point;
import java.awt.Rectangle;

import cn.wn.tankwar.Directions;

/**
 * ̹��ģ����
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
	 * ̹�˵Ĺ��췽��
	 * 
	 * @param x
	 *            ���Ͻ�x����
	 * @param y
	 *            ���Ͻ�y����
	 * @param width
	 *            ���
	 * @param height
	 *            �߶�
	 * @param controller
	 *            ������
	 * @param view
	 *            ��ͼ
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
	 * ���̹�˵����ĵ�
	 * 
	 * @return ����Point��
	 */
	public Point getCenterPoint() {
		return centerPoint;
	}

	/**
	 * ��ȡ̹�˵Ŀ�����
	 * 
	 * @return TankController
	 */
	public PlayerTankController getController() {
		return controller;
	}

	/**
	 * ���̹�˵ķ���
	 * 
	 * @return
	 */
	public Directions getDirection() {
		return direction;
	}

	/**
	 * ���̹�˵ĸ߶�
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * ��ȡ̹����ͼ��ķ���
	 * 
	 * @return TankView
	 */
	public PlayerTankView getView() {
		return view;
	}

	/**
	 * ���̹�˵ĸ߶�
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * ���̹�����Ͻǵ�X����
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * ���̹�����Ͻǵ�Y����
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * �����¼�ͷ���Ƿ���
	 * 
	 * @return
	 */
	public boolean isDownPressed() {
		return downPressed;
	}

	/**
	 * �������ͷ���Ƿ���
	 * 
	 * @return
	 */
	public boolean isLeftPressed() {
		return leftPressed;
	}

	/**
	 * �����Ҽ�ͷ�Ƿ���
	 * 
	 * @return
	 */
	public boolean isRightPressed() {
		return rightPressed;
	}

	/**
	 * �����ϼ�ͷ�Ƿ���
	 * 
	 * @return
	 */
	public boolean isUpPressed() {
		return upPressed;
	}

	/**
	 * ����̹�˵Ŀ�����
	 * 
	 * @param controller
	 *            TankController
	 */
	public void setController(PlayerTankController controller) {
		this.controller = controller;
	}

	/**
	 * ���÷���
	 * 
	 * @param direction
	 */
	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	/**
	 * �����°���״̬
	 * 
	 * @param downPressed
	 */
	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}

	/**
	 * ����̹�˵Ĵ�С
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * �������ͷ����״̬
	 * 
	 * @param leftPressed
	 */
	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	/**
	 * �����Ҽ�ͷ����״̬
	 * 
	 * @param rightPressed
	 */
	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	/**
	 * �����ϼ�ͷ����״̬
	 * 
	 * @param upPressed
	 */
	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	/**
	 * ����̹�˵���ͼ��
	 * 
	 * @param view
	 *            ̹����ͼ
	 */
	public void setView(PlayerTankView view) {
		this.view = view;
	}

	/**
	 * ����̹�˵Ŀ��
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * ����̹�����Ͻ�X����,ͬʱ�������ĵ�X����
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
		updateCenter();
	}

	/**
	 * ����̹�����Ͻ�Y����,ͬʱ�������ĵ�Y����
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
