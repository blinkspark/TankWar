package cn.wn.tankwar.tank;

import java.awt.Point;

/**
 * ̹��ģ����
 * @author Wangning
 *
 */
public class Tank {

	/**
	 * ̹�˵Ĺ��췽��
	 * @param x ���Ͻ�x����
	 * @param y ���Ͻ�y����
	 * @param width ���
	 * @param height �߶�
	 * @param controller ������
	 * @param view ��ͼ
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
	 * ��ȡ̹����ͼ��ķ���
	 * @return TankView
	 */
	public TankView getView() {
		return view;
	}

	/**
	 * ����̹�˵���ͼ��
	 * @param view ̹����ͼ��
	 */
	public void setView(TankView view) {
		this.view = view;
	}

	/**
	 * ����̹�˵Ŀ�����
	 * @param controller TankController
	 */
	public void setController(TankController controller) {
		this.controller = controller;
	}
	
	/**
	 * ��ȡ̹�˵Ŀ�����
	 * @return TankController
	 */
	public TankController getController() {
		return controller;
	}

}
