package cn.wn.tankwar;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

/**
 * ��������
 * @author Wangning
 *
 */
public class TankClient extends JFrame {
	
	private static final long serialVersionUID = 6432091120610414896L;

	private static final int SCR_HEIGHT = 600;
	public static final int SCR_WIDTH = 800;
	private DisplayMode defaultDisplayMode;

	private GraphicsDevice device;

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
	}

	/**
	 * ��ͼ����
	 */
	@Override
	public void paint(Graphics g) {
		backGroundLayer(g);
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
				if(device.isDisplayChangeSupported()){
					device.setDisplayMode(defaultDisplayMode);
				}
				device.setFullScreenWindow(null);
				break;

			default:
				break;
			}
		}

		
	}

}
