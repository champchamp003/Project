package com.myoop.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myoop.game.OOProject;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = OOProject.WIDTH;
		config.height = OOProject.HEIGHT;
		config.title = OOProject.TITLE;
		new LwjglApplication(new OOProject(), config);
	}
}
