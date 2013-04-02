package cn.wn.tankwar;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

/**
 * ��������
 * @author Wangning
 *
 */
public class TankClient extends JFrame {

	private static final int SCR_HEIGHT = 600;
	public static final int SCR_WIDTH = 800;
	private static final long serialVersionUID = 6432091120610414896L;

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
		GraphicsDevice device = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		DisplayMode displayMode = device.getDisplayMode();
		device.setFullScreenWindow(client);
		if (device.isDisplayChangeSupported()) {
			device.setDisplayMode(new DisplayMode(SCR_WIDTH, SCR_HEIGHT, displayMode
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
	
	

}
