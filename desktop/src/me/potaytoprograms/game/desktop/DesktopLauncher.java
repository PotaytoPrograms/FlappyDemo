package me.potaytoprograms.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.potaytoprograms.game.FlappyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = FlappyDemo.HIGHT;
		config.width = FlappyDemo.WIDTH;
		config.title = FlappyDemo.TITLE;
		config.resizable = false;
		new LwjglApplication(new FlappyDemo(), config);
	}
}
