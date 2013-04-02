package cn.wn.tankwar;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class TankClient extends JFrame {

	private static final long serialVersionUID = 6432091120610414896L;

	public static void main(String[] args) {
		TankClient client = new TankClient();
		client.launchFrame();

		client.setFullScreen(client);
	}

	private void setFullScreen(TankClient client) {
		GraphicsDevice device = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		DisplayMode displayMode = device.getDisplayMode();
		if (device.isDisplayChangeSupported()) {
			device.setDisplayMode(new DisplayMode(800, 600, displayMode
					.getBitDepth(), DisplayMode.REFRESH_RATE_UNKNOWN));
		}
		device.setFullScreenWindow(client);
	}

	public void launchFrame() {
		setTitle("TankWar");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);

		GraphicsDevice device = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		DisplayMode displayMode = device.getDisplayMode();
		if (device.isDisplayChangeSupported()) {
			device.setDisplayMode(new DisplayMode(800, 600, displayMode
					.getBitDepth(), DisplayMode.REFRESH_RATE_UNKNOWN));
		}
		device.setFullScreenWindow(this);
	}

}
