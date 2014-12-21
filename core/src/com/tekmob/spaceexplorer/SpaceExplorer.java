package com.tekmob.spaceexplorer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tekmob.spaceexplorer.Screen.SplashScreen;

public class SpaceExplorer extends Game {

    public static int WIDTH = 1280, HEIGHT = 720;
    private GamePreferences preferences;
    private ScreenStackManager screenstack;

    @Override
    public void create() {
    	Gdx.input.setCatchBackKey(true);

        preferences = new GamePreferences();
    	Assets.load();
    	
        screenstack = new ScreenStackManager(this, new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }

    public GamePreferences getPreferences(){
        return preferences;
    }
    
	public ScreenStackManager getScreenstack() {
		return screenstack;
	}
}
