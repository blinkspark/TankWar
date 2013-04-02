package cn.wn.tankwar;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import cn.wn.tankwar.tank.Tank;
import cn.wn.tankwar.tank.TankView;

/**
 * ��������
 * @author Wangning
 *
 */
public class TankClient extends JFrame {
	
	private static final int REFRESH_SEQUENCE = 1000/30;

	private static final long serialVersionUID = 6432091120610414896L;

	public static final int SCR_HEIGHT = 600;
	public static final int SCR_WIDTH = 800;

	private DisplayMode defaultDisplayMode;
	private GraphicsDevice device;
	private ArrayList<Tank> tanks = new ArrayList<>();

	/**
	 * �������
	 * @param args �����в���
	 */
	public static void main(String[] args) {
		TankClient client = new TankClient();
		client.launchFrame();

		client.setFullScreen(client);
	}

	/**
	 * ����ȫ������
	 * @param client Ҫ����ȫ���Ĵ���
	 */
	private void setFullScreen(TankClient client) {
		device = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		defaultDisplayMode = device.getDisplayMode();
		device.setFullScreenWindow(client);
		if (device.isDisplayChangeSupported()) {
			device.setDisplayMode(new DisplayMode(SCR_WIDTH, SCR_HEIGHT, defaultDisplayMode
					.getBitDepth(), DisplayMode.REFRESH_RATE_UNKNOWN));
		}
	}

	/**
	 * �������ڷ���
	 */
	public void launchFrame() {
		setTitle("TankWar");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(SCR_WIDTH, SCR_HEIGHT);
		setVisible(true);
		addKeyListener(new GameKeyListener());
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new RefreshTask(), new Date() , REFRESH_SEQUENCE);
		
		tanks.add(new Tank(100, 100, 40, 40, null, new TankView()));
	}

	/**
	 * ��ʱˢ����
	 * @author Wangning
	 *
	 */
	class RefreshTask extends TimerTask{

		/**
		 * ��дrun������ʱ����ִ�з���
		 */
		@Override
		public void run() {
			repaint();
		}
		
	}
	
	/**
	 * ��ͼ����
	 */
	@Override
	public void paint(Graphics g) {
		backGroundLayer(g);
		
		for(Tank t:tanks){
			t.getView();
		}
	}
	/**
	 * ����ͼ��
	 * @param g ���ڵĻ�ͼ
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
		if(device.isDisplayChangeSupported()){
			device.setDisplayMode(defaultDisplayMode);
		}
		device.setFullScreenWindow(null);
	}
	/**
	 * ���̼�����
	 * @author Wangning
	 *
	 */
	class GameKeyListener extends KeyAdapter{

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
				if(e.isAltDown()){
					if(device.getFullScreenWindow()==null){
						setFullScreen(TankClient.this);
					}else {
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
