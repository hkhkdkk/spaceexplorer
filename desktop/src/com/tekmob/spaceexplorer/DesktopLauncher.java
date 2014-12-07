package com.tekmob.spaceexplorer;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tekmob.spaceexplorer.SpaceExplorer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = SpaceExplorer.WIDTH;
        config.height = SpaceExplorer.HEIGHT;
		new LwjglApplication(new SpaceExplorer(), config);
	}
}
