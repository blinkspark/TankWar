package cn.wn.tankwar.tank;

import java.awt.Point;

/**
 * ̹��ģ����
 * @author Wangning
 *
 */
public class Tank {

	protected Point centerPoint;
	protected int width;
	protected int height;
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
