package com.tekmob.spaceexplorer;

import com.badlogic.gdx.Game;
import com.tekmob.spaceexplorer.Screen.SplashScreen;

public class SpaceExplorer extends Game {
    public static int WIDTH = 800, HEIGHT = 480;

    private ScreenStackManager screenstack;


    @Override
    public void create() {
        Assets.load();
        
        screenstack = new ScreenStackManager(this);
        screenstack.push(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        while(!screenstack.isEmpty()) {
        	screenstack.pop();
        }
        Assets.dispose();
    }

	public ScreenStackManager getScreenstack() {
		return screenstack;
	}
    
    
}
