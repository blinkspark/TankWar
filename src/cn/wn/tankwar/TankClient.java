package cn.wn.tankwar;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import cn.wn.tankwar.tank.Tank;
import cn.wn.tankwar.tank.TankController;
import cn.wn.tankwar.tank.TankView;

/**
 * 主窗口类
 * 
 * @author Wangning
 * 
 */
public class TankClient extends Frame {

	private static final int REFRESH_SEQUENCE = 1000 / 30;

	private static final long serialVersionUID = 6432091120610414896L;

	public static final int SCR_HEIGHT = 600;
	public static final int SCR_WIDTH = 800;

	private DisplayMode defaultDisplayMode;
	private GraphicsDevice device;
	private ArrayList<Tank> tanks = new ArrayList<>();

	private Image bufferImage = null;

	/**
	 * 程序入口
	 * 
	 * @param args
	 *            命令行参数
	 */
	public static void main(String[] args) {
		TankClient client = new TankClient();
		client.launchFrame();

		client.setFullScreen(client);
	}

	/**
	 * 设置全屏方法
	 * 
	 * @param client
	 *            要设置全屏的窗口
	 */
	private void setFullScreen(TankClient client) {
		device = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		defaultDisplayMode = device.getDisplayMode();
		device.setFullScreenWindow(client);
		if (device.isDisplayChangeSupported()) {
			device.setDisplayMode(new DisplayMode(SCR_WIDTH, SCR_HEIGHT,
					defaultDisplayMode.getBitDepth(),
					DisplayMode.REFRESH_RATE_UNKNOWN));
		}
	}

	/**
	 * 启动窗口方法
	 */
	public void launchFrame() {
		setTitle("TankWar");
		setSize(SCR_WIDTH, SCR_HEIGHT);
		setVisible(true);
		addKeyListener(new GameKeyListener());
		addWindowListener(new GameWindowListener());
		setResizable(false);

		tanks.add(new Tank(100, 100, 40, 40, new TankController(), new TankView()));
		new RefreshThread().start();
	}

	class GameWindowListener extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

	}

	/**
	 * 定时刷新类
	 * 
	 * @author Wangning
	 * 
	 */
	class RefreshThread extends Thread {

		/**
		 * 重写run方法定时器的执行方法
		 */
		@Override
		public void run() {
			while (true) {
				tanks.get(0).getController().move();
				repaint();
				try {
					Thread.sleep(REFRESH_SEQUENCE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(Graphics g) {
		if (bufferImage == null) {
			bufferImage = createImage(SCR_WIDTH, SCR_HEIGHT);
		}
		Graphics bufferGraphics = bufferImage.getGraphics();

		paint(bufferGraphics);

		g.drawImage(bufferImage, 0, 0, null);
	}

	/**
	 * 绘图方法
	 */
	@Override
	public void paint(Graphics g) {
		backGroundLayer(g);

		for (Tank tank : tanks) {
			tank.getView().draw(g);
		}
	}

	/**
	 * 背景图层
	 * 
	 * @param g
	 *            窗口的绘图
	 */
	private void backGroundLayer(Graphics g) {
		Color defColor = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, SCR_WIDTH, SCR_HEIGHT);
		g.setColor(defColor);
	}

	/**
	 * 关闭全屏方法
	 */
	private void closeFullScreen() {
		if (device.isDisplayChangeSupported()) {
			device.setDisplayMode(defaultDisplayMode);
		}
		device.setFullScreenWindow(null);
	}

	/**
	 * 键盘监听类
	 * 
	 * @author Wangning
	 * 
	 */
	class GameKeyListener extends KeyAdapter {


		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				tanks.get(0).setUpPressed(true);
				break;
			case KeyEvent.VK_DOWN:
				tanks.get(0).setDownPressed(true);
				break;
			case KeyEvent.VK_LEFT:
				tanks.get(0).setLeftPressed(true);
				break;
			case KeyEvent.VK_RIGHT:
				tanks.get(0).setRightPressed(true);
				break;

			default:
				break;
			}
		}

		/**
		 * 重写keyRelease方法监听键盘释放事件
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_ESCAPE:
				if (device.getFullScreenWindow() != null) {
					closeFullScreen();
				} else {
					System.exit(0);
				}
				break;
			case KeyEvent.VK_ENTER:
				if (e.isAltDown()) {
					if (device.getFullScreenWindow() == null) {
						setFullScreen(TankClient.this);
					} else {
						closeFullScreen();
					}
				}
				break;
			case KeyEvent.VK_UP:
				tanks.get(0).setUpPressed(false);
				break;
			case KeyEvent.VK_DOWN:
				tanks.get(0).setDownPressed(false);
				break;
			case KeyEvent.VK_LEFT:
				tanks.get(0).setLeftPressed(false);
				break;
			case KeyEvent.VK_RIGHT:
				tanks.get(0).setRightPressed(false);
				break;
			default:
				break;
			}
		}

	}

}
