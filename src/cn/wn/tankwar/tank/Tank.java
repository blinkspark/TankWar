package cn.wn.tankwar.tank;

import java.awt.Image;
import java.awt.Point;

/**
 * ̹��ģ����
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
	 * ̹�˵Ĺ��췽��
	 * @param x ���Ͻ�x����
	 * @param y ���Ͻ�y����
	 * @param width ���
	 * @param height �߶�
	 * @param controller ������
	 * @param view ��ͼ
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
	 * ��ȡ̹�˵Ŀ�����
	 * @return TankController
	 */
	public TankController getController() {
		return controller;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * ��ȡ̹����ͼ��ķ���
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
	 * ����̹�˵Ŀ�����
	 * @param controller TankController
	 */
	public void setController(TankController controller) {
		this.controller = controller;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * ����̹�˵���ͼ��
	 * @param view ̹����ͼ��
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
