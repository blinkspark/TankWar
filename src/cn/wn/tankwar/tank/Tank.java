package cn.wn.tankwar.tank;

import java.awt.Point;

/**
 * 坦克模型类
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
