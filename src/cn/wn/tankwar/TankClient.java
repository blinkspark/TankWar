package cn.wn.tankwar;

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

import cn.wn.tankwar.explode.Explode;
import cn.wn.tankwar.explode.ExplodeView;
import cn.wn.tankwar.missile.Missile;
import cn.wn.tankwar.missile.MissileController;
import cn.wn.tankwar.missile.MissileView;
import cn.wn.tankwar.obtacle.Obtacle;
import cn.wn.tankwar.obtacle.ObtacleView;
import cn.wn.tankwar.resource.R;
import cn.wn.tankwar.tank.PlayerTank;
import cn.wn.tankwar.tank.PlayerTankController;
import cn.wn.tankwar.tank.PlayerTankView;

/**
 * 主窗口类
 * 
 * @author Wangning
 * 
 */
public class TankClient extends Frame {

	private static final int TANK_SIZE = 48;

	/**
	 * 刷新频率
	 */
	private static final int REFRESH_SEQUENCE = 1000 / 30;

	private static final long serialVersionUID = 6432091120610414896L;

	public static final int SCR_HEIGHT = 600;
	public static final int SCR_WIDTH = 800;

	public Obtacle obtacle;
	private GraphicsDevice device = null;
	private DisplayMode defaultDisplayMode = null;
	public ArrayList<PlayerTank> tanks = new ArrayList<>();
	public Missile missile;
	public Explode explode;

	public TankClient() {
		device = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		defaultDisplayMode = device.getDisplayMode();
	}

	private Image bufferImage = null;

	private RefreshThread refreshThread;

	/**
	 * 程序入口
	 * 
	 * @param args
	 *            命令行参数
	 */
	public static void main(String[] args) {
		TankClient client = new TankClient();
		client.launchFrame();

		// client.setFullScreen(client);
	}

	/**
	 * 设置全屏方法
	 * 
	 * @param client
	 *            要设置全屏的窗口对象
	 */
	private void setFullScreen(TankClient client) {
		device.setFullScreenWindow(client);
		if (device.isDisplayChangeSupported()) {
			device.setDisplayMode(new DisplayMode(SCR_WIDTH, SCR_HEIGHT,
					defaultDisplayMode.getBitDepth(),
					DisplayMode.REFRESH_RATE_UNKNOWN));
		}
	}

	public Obtacle getObtacle() {
		return obtacle;
	}

	public ArrayList<PlayerTank> getTanks() {
		return tanks;
	}

	public Missile getMissile() {
		return missile;
	}

	/**
	 * 启动窗口方法
	 */
	public void launchFrame() {
		R.Drawable.init();
		setTitle("TankWar");
		setLocation(100, 100);
		setSize(SCR_WIDTH, SCR_HEIGHT);
		setVisible(true);
		addKeyListener(new GameKeyListener());
		addWindowListener(new GameWindowListener());
		setResizable(false);

		tanks.add(new PlayerTank(100, 100, TANK_SIZE, TANK_SIZE, new PlayerTankController(
				this), new PlayerTankView()));

		explode = new Explode(200, 400, 56, 56, new ExplodeView());
		obtacle = new Obtacle(400, 400, 48, 48, new ObtacleView());
		missile = new Missile(200, 200, 40, 40, new MissileController(this),
				new MissileView(), Directions.RD);
		refreshThread = new RefreshThread();
		refreshThread.start();
	}

	/**
	 * 窗口监听类,监听退出等事件
	 * 
	 * @author Wangning
	 * 
	 */
	class GameWindowListener extends WindowAdapter {

		/**
		 * 程序退出方法
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			try {
				refreshThread.setExitFlag(true);
				refreshThread.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
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

		private boolean exit = false;

		public void setExitFlag(boolean exit) {
			this.exit = exit;
		}

		/**
		 * 重写run方法定时器的执行方法
		 */
		@Override
		public void run() {
			while (!exit) {
				tanks.get(0).getController().move();
				missile.getController().move();
				repaint();
				try {
					Thread.sleep(REFRESH_SEQUENCE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 执行刷新时实际会调用的方法,该方法再调用paint 本方法实现了双缓冲
	 * 
	 * @see cn.wn.tankwar.TankClient.paint()
	 */
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
		obtacle.getView().draw(g);
		missile.getView().draw(g);
		explode.getView().draw(g);

		for (PlayerTank tank : tanks) {
			tank.getView().draw(g);
		}

	}

	/**
	 * 背景图层
	 * 
	 * @param g
	 *            窗口的画笔
	 */
	private void backGroundLayer(Graphics g) {
		//绿色背景
		// Color defColor = g.getColor();
		// g.setColor(Color.GREEN);
		// g.fillRect(0, 0, SCR_WIDTH, SCR_HEIGHT);
		// g.setColor(defColor);
		
		//图片背景
		for (int x = 0; x < SCR_WIDTH; x += 256) {
			for (int y = 0; y < SCR_HEIGHT; y += 256) {
				g.drawImage(R.Drawable.backgroundImage, x, y, null);
			}
		}
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

		/**
		 * 监听键盘的按下事件
		 */
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
			case KeyEvent.VK_CONTROL:
			case KeyEvent.VK_SPACE:
				tanks.get(0).getController().fire();
			default:
				break;
			}
		}

	}

}
