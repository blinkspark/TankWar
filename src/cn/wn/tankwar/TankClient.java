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
import java.util.Date;
import java.util.Random;

import cn.wn.tankwar.explode.Explode;
import cn.wn.tankwar.missile.Missile;
import cn.wn.tankwar.obtacle.Obtacle;
import cn.wn.tankwar.obtacle.ObtacleView;
import cn.wn.tankwar.resource.R;
import cn.wn.tankwar.tank.EnemyTankController;
import cn.wn.tankwar.tank.EnemyTankView;
import cn.wn.tankwar.tank.PlayerTankController;
import cn.wn.tankwar.tank.PlayerTankView;
import cn.wn.tankwar.tank.Tank;

/**
 * ��������
 * 
 * @author Wangning
 * 
 */
public class TankClient extends Frame {

	private static final int STRING_Y = 50;

	private static final int STRING_X = 20;

	public static final int ENEMY_TANK_HEALTH = 1;

	public static final int PLAYER_TANK_HEALTH = 3;

	public static final int TANK_SIZE = 48;

	/**
	 * ˢ��Ƶ��
	 */
	public static final int REFRESH_SEQUENCE = 1000 / 30;

	private static final long serialVersionUID = 6432091120610414896L;

	public static final int SCR_HEIGHT = 600;
	public static final int SCR_WIDTH = 800;

	public Random random = new Random();
	public Obtacle obtacle;
	private GraphicsDevice device = null;
	private DisplayMode defaultDisplayMode = null;
	public ArrayList<Tank> tanks = new ArrayList<>();
	public ArrayList<Explode> explodes = new ArrayList<>();
	public ArrayList<Missile> missiles = new ArrayList<>();
	public boolean playerTankAlive;

	public TankClient() {
		device = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		defaultDisplayMode = device.getDisplayMode();
		playerTankAlive = true;
	}

	private Image bufferImage = null;

	private RefreshThread refreshThread;

	public int lifes = 5;

	public int enemytankCount = 100;

	private int line = 0;

	private static final int LINE_HEIGHT = 20;

	/**
	 * �������
	 * 
	 * @param args
	 *            �����в���
	 */
	public static void main(String[] args) {
		TankClient client = new TankClient();
		client.launchFrame();

		// client.setFullScreen(client);
	}

	/**
	 * ����ȫ������
	 * 
	 * @param client
	 *            Ҫ����ȫ���Ĵ��ڶ���
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

	public ArrayList<Tank> getTanks() {
		return tanks;
	}

	public ArrayList<Missile> getMissiles() {
		return missiles;
	}

	/**
	 * �������ڷ���
	 */
	public void launchFrame() {
		R.Drawable.init();
		R.Audio.init();
		setTitle("TankWar");
		setLocation(100, 100);
		setSize(SCR_WIDTH, SCR_HEIGHT);
		setVisible(true);
		addKeyListener(new GameKeyListener());
		addWindowListener(new GameWindowListener());
		setResizable(false);

		tanks.add(new Tank(100, 100, TANK_SIZE, TANK_SIZE,
				new PlayerTankController(this), new PlayerTankView(), true,
				PLAYER_TANK_HEALTH));
		for (int i = 0; i < 8; i++) {
			synchronized (tanks) {
				tanks.add(new Tank(random.nextInt(SCR_WIDTH - TANK_SIZE),
						random.nextInt(SCR_HEIGHT - TANK_SIZE), TANK_SIZE,
						TANK_SIZE, new EnemyTankController(this),
						new EnemyTankView(), false, ENEMY_TANK_HEALTH));
			}
		}

		obtacle = new Obtacle(400, 400, 48, 48, new ObtacleView());
		refreshThread = new RefreshThread();
		refreshThread.start();

		R.Audio.bgmAudioClip.loop();
	}

	/**
	 * ���ڼ�����,�����˳����¼�
	 * 
	 * @author Wangning
	 * 
	 */
	class GameWindowListener extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			exit();
		}

	}

	/**
	 * ��ʱˢ����
	 * 
	 * @author Wangning
	 * 
	 */
	class RefreshThread extends Thread {

		private boolean exit = false;
		private boolean pause = true;

		public void setExitFlag(boolean exit) {
			this.exit = exit;
		}

		/**
		 * ��дrun������ʱ����ִ�з���
		 */
		@Override
		public void run() {
			while (!exit) {
				if (pause) {
					try {
						Thread.sleep(REFRESH_SEQUENCE);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}
				synchronized (tanks) {
					if (getAICount(tanks) < 4 && enemytankCount > 0) {
						for (int i = 0; i < 4; i++) {
							enemytankCount--;
							tanks.add(new Tank(random.nextInt(SCR_WIDTH
									- TANK_SIZE), random.nextInt(SCR_HEIGHT
									- TANK_SIZE), TANK_SIZE, TANK_SIZE,
									new EnemyTankController(TankClient.this),
									new EnemyTankView(), false,
									ENEMY_TANK_HEALTH));
						}
					}
					for (int i = 0; i < tanks.size(); i++) {
						Tank tank = tanks.get(i);
						if (tank.isAlive()) {
							tank.getController().move();
						} else {
							tanks.remove(i);
							if (tank.isGood()) {
								playerTankAlive = false;
							}
							i--;
						}
					}
				}
				for (int i = 0; i < missiles.size(); i++) {
					Missile missile = missiles.get(i);
					if (missile.isAlive()) {
						missile.getController().move();
					} else {
						missiles.remove(i--);
					}
				}
				repaint();
				try {
					Thread.sleep(REFRESH_SEQUENCE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private int getAICount(ArrayList<Tank> tanks) {
			int count = 0;
			for (int i = 0; i < tanks.size(); i++) {
				if (!tanks.get(i).isGood()) {
					count++;
				}
			}
			return count;
		}

		public boolean isPause() {
			return pause;
		}

		public void setPause(boolean pause) {
			this.pause = pause;
		}

	}

	/**
	 * ִ��ˢ��ʱʵ�ʻ���õķ���,�÷����ٵ���paint ������ʵ����˫����
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
	 * ��ͼ����
	 */
	@Override
	public void paint(Graphics g) {
		backGroundLayer(g);
		obtacle.getView().draw(g);
		synchronized (missiles) {
			for (Missile missile : missiles) {
				missile.getView().draw(g);
			}
		}

		for (Explode explode : explodes) {
			explode.getView().draw(g);
		}

		synchronized (tanks) {
			for (Tank tank : tanks) {
				tank.getView().draw(g);
			}
		}

		// ̹������
		String lifesString = "lifes: " + lifes;
		g.drawString(lifesString, STRING_X, STRING_Y + line * LINE_HEIGHT);

		// ̹������ֵ
		String healthString = "HP: " + getTankHealth();
		g.drawString(healthString, STRING_X, STRING_Y + (line+1) * LINE_HEIGHT);

		// �з�̹����
		String enemyTankString = "�з�̹��ʣ��: " + enemytankCount;
		g.drawString(enemyTankString, STRING_X, STRING_Y + (line + 2)
				* LINE_HEIGHT);

		// ������ȴʱ��
		String shieldCDString = "������ȴ: " + getShieldCoolDown();
		g.drawString(shieldCDString, STRING_X, STRING_Y + (line + 3)
				* LINE_HEIGHT);

		// Ӱ������ȴʱ��
		String shadowCloneCDString = "Ӱ������ȴ: " + getShadowCloneCoolDown();
		g.drawString(shadowCloneCDString, STRING_X, STRING_Y + (line + 4)
				* LINE_HEIGHT);
	}

	private int getTankHealth() {
		int health = 0;
		for (int i = 0; i < tanks.size(); i++) {
			Tank tank = tanks.get(i);
			if(tank.getController().isPlayerTank()){
				health = tank.getHealth();
			}
		}
		return health;
	}

	private int getShadowCloneCoolDown() {
		long cd_ms = 0;
		for (int i = 0; i < tanks.size(); i++) {
			Tank tank = tanks.get(i);
			if (tank.getController().isPlayerTank()) {
				cd_ms = tank.getController().getShadowCloneCoolDownDate().getTime()-new Date().getTime();
			}
		}
		
		int cd_s = (int) (cd_ms/1000);
		if (cd_s < 0) {
			cd_s = 0;
		}

		return cd_s;
	}

	private int getShieldCoolDown() {
		long cd_ms = 0;
		for (int i = 0; i < tanks.size(); i++) {
			Tank tank = tanks.get(i);
			if (tank.getController().isPlayerTank()) {
				cd_ms = tank.getShield().getCoolDownDate().getTime()
						- new Date().getTime();
			}
		}
		int cd_s = (int) (cd_ms / 1000);
		if(cd_s<0){
			cd_s = 0;
		}
		
		return cd_s;
	}

	/**
	 * ����ͼ��
	 * 
	 * @param g
	 *            ���ڵĻ���
	 */
	private void backGroundLayer(Graphics g) {
		// ��ɫ����
		// Color defColor = g.getColor();
		// g.setColor(Color.GREEN);
		// g.fillRect(0, 0, SCR_WIDTH, SCR_HEIGHT);
		// g.setColor(defColor);

		// ͼƬ����
		for (int x = 0; x < SCR_WIDTH; x += 256) {
			for (int y = 0; y < SCR_HEIGHT; y += 256) {
				g.drawImage(R.Drawable.backgroundImage, x, y, null);
			}
		}
	}

	/**
	 * �ر�ȫ������
	 */
	private void closeFullScreen() {
		if (device.isDisplayChangeSupported()) {
			device.setDisplayMode(defaultDisplayMode);
		}
		device.setFullScreenWindow(null);
	}

	/**
	 * �����˳�����
	 */
	private void exit() {
		try {
			refreshThread.setExitFlag(true);
			refreshThread.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * ���̼�����
	 * 
	 * @author Wangning
	 * 
	 */
	class GameKeyListener extends KeyAdapter {

		/**
		 * �������̵İ����¼�
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				synchronized (tanks) {
					for (Tank tank : tanks) {
						tank.setUpPressed(true);
					}
				}
				break;
			case KeyEvent.VK_DOWN:
				synchronized (tanks) {
					for (Tank tank : tanks) {
						tank.setDownPressed(true);
					}
				}
				break;
			case KeyEvent.VK_LEFT:
				synchronized (tanks) {
					for (Tank tank : tanks) {
						tank.setLeftPressed(true);
					}
				}
				break;
			case KeyEvent.VK_RIGHT:
				synchronized (tanks) {
					for (Tank tank : tanks) {
						tank.setRightPressed(true);
					}
				}
				break;

			default:
				break;
			}
		}

		/**
		 * ��дkeyRelease�������������ͷ��¼�
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_F2:
				if (!playerTankAlive && lifes > 0) {
					playerTankAlive = true;
					lifes--;
					synchronized (tanks) {
						tanks.add(new Tank(random
								.nextInt(SCR_WIDTH - TANK_SIZE), random
								.nextInt(SCR_HEIGHT - TANK_SIZE), TANK_SIZE,
								TANK_SIZE, new PlayerTankController(
										TankClient.this), new PlayerTankView(),
								true, PLAYER_TANK_HEALTH));
					}
				}
				break;
			case KeyEvent.VK_W:
				synchronized (tanks) {
					for (Tank tank : tanks) {
						if (tank.isGood()) {
							tank.getController().shieldOn();
						}
					}
				}
				break;
			case KeyEvent.VK_R:
				synchronized (tanks) {
					for (Tank tank : tanks) {
						if (tank.isGood()) {
							tank.getController().shadowClone();
						}
					}
				}
				break;
			case KeyEvent.VK_ESCAPE:
				if (device.getFullScreenWindow() != null) {
					closeFullScreen();
				} else {
					exit();
				}
				break;
			case KeyEvent.VK_ENTER:
				if (e.isAltDown()) {
					if (device.getFullScreenWindow() == null) {
						setFullScreen(TankClient.this);
					} else {
						closeFullScreen();
					}
				} else {
					refreshThread.setPause(!refreshThread.isPause());
				}
				break;
			case KeyEvent.VK_UP:
				synchronized (tanks) {
					for (Tank tank : tanks) {
						tank.setUpPressed(false);
					}
				}
				break;
			case KeyEvent.VK_DOWN:
				synchronized (tanks) {
					for (Tank tank : tanks) {
						tank.setDownPressed(false);
					}
				}
				break;
			case KeyEvent.VK_LEFT:
				synchronized (tanks) {
					for (Tank tank : tanks) {
						tank.setLeftPressed(false);
					}
				}
				break;
			case KeyEvent.VK_RIGHT:
				synchronized (tanks) {
					for (Tank tank : tanks) {
						tank.setRightPressed(false);
					}
				}
				break;
			case KeyEvent.VK_CONTROL:
			case KeyEvent.VK_SPACE:
				synchronized (tanks) {
					for (Tank tank : tanks) {
						if (tank.isGood()) {
							tank.getController().fire();
						}
					}
				}
			default:
				break;
			}
		}

	}

}
