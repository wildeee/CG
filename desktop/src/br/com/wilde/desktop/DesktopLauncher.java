package br.com.wilde.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.com.wilde.Boot;
import br.com.wilde.Joystick;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Joystick joystick = new DesktopInputListener();
		Boot starter = new Boot(joystick);
		new LwjglApplication(starter, config);
	}
}