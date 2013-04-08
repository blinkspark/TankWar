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
import java.util.ArrayList;

import cn.wn.tankwar.tank.Tank;
import cn.wn.tankwar.tank.TankView;

/**
 * ��������
 * 
 * @author Wangning
 * 
 */
public class TankClient extends Frame {

	private static final int REFRESH_SEQUENCE = 1000/30;

	private static final long serialVersionUID = 6432091120610414896L;

	public static final int SCR_HEIGHT = 600;
	public static final int SCR_WIDTH = 800;

	private DisplayMode defaultDisplayMode;
	private GraphicsDevice device;
	private ArrayList<Tank> tanks = new ArrayList<>();

	private Image bufferImage = null;

	/**
	 * �������
	 * 
	 * @param args
	 *            �����в���
	 */
	public static void main(String[] args) {
		TankClient client = new TankClient();
		client.launchFrame();

		client.setFullScreen(client);
	}

	/**
	 * ����ȫ������
	 * 
	 * @param client
	 *            Ҫ����ȫ���Ĵ���
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
	 * �������ڷ���
	 */
	public void launchFrame() {
		setTitle("TankWar");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(SCR_WIDTH, SCR_HEIGHT);
		setVisible(true);
		addKeyListener(new GameKeyListener());
		// Timer timer = new Timer();
		// timer.schedule(new RefreshTask(), 0 , REFRESH_SEQUENCE);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					repaint();
					try {
						Thread.sleep(REFRESH_SEQUENCE);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

		tanks.add(new Tank(100, 100, 40, 40, null, new TankView()));
	}

	/**
	 * ��ʱˢ����
	 * 
	 * @author Wangning
	 * 
	 */
	class RefreshThread extends Thread {

		/**
		 * ��дrun������ʱ����ִ�з���
		 */
		@Override
		public void run() {
			while (true) {
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
	 * ��ͼ����
	 */
	@Override
	public void paint(Graphics g) {
		backGroundLayer(g);

		for (Tank tank : tanks) {
			tank.getView().draw(g);
		}
	}

	/**
	 * ����ͼ��
	 * 
	 * @param g
	 *            ���ڵĻ�ͼ
	 */
	private void backGroundLayer(Graphics g) {
		Color defColor = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, SCR_WIDTH, SCR_HEIGHT);
		g.setColor(defColor);
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
	 * ���̼�����
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
				tanks.get(0).setY(tanks.get(0).getY()-2);
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
			case KeyEvent.VK_ESCAPE:
				closeFullScreen();
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

			default:
				break;
			}
		}

	}

}
