package com.tekmob.spaceexplorers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tekmob.spaceexplorers.Controller.PreferenceController;
import com.tekmob.spaceexplorers.Screen.SplashScreen;

public class SpaceExplorer extends Game {

    public static int WIDTH = 1280, HEIGHT = 720;
    private ScreenStackManager screenstack;
    private PreferenceController prefs;

    @Override
    public void create() {
    	Gdx.input.setCatchBackKey(true);
        prefs = new PreferenceController();

    	Assets.load();
        screenstack = new ScreenStackManager(this, new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }
    
	public ScreenStackManager getScreenstack() {
		return screenstack;
	}

    public PreferenceController getPrefController(){
        return prefs;
    }
}
