package com.tekmob.spaceexplorer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tekmob.spaceexplorer.Screen.EncyclopediaScreen;
import com.tekmob.spaceexplorer.Screen.GameScreen;
import com.tekmob.spaceexplorer.Screen.HelpScreen;
import com.tekmob.spaceexplorer.Screen.HighscoreScreen;
import com.tekmob.spaceexplorer.Screen.LoadingScreen;
import com.tekmob.spaceexplorer.Screen.MenuScreen;
import com.tekmob.spaceexplorer.Screen.SettingScreen;
import com.tekmob.spaceexplorer.Screen.SplashScreen;

import sun.rmi.runtime.Log;

public class SpaceExplorer extends Game {

    private SplashScreen splashScreen;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private SettingScreen settingScreen;
    private HighscoreScreen highscoreScreen;
    private EncyclopediaScreen encyclopediaScreen;
    private HelpScreen helpScreen;


    public SpaceExplorer(){}

    @Override
    public void create() {
        Assets.load();

        splashScreen = new SplashScreen(this);
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        settingScreen = new SettingScreen(this);
        highscoreScreen = new HighscoreScreen(this);
        encyclopediaScreen = new EncyclopediaScreen(this);
        helpScreen = new HelpScreen(this);

        setScreen(getSplashScreen());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

    public SplashScreen getSplashScreen(){
        return splashScreen;
    }

    public MenuScreen getMenuScreen(){
        return menuScreen;
    }

    public GameScreen getGameScreen(){
        return gameScreen;
    }

    public SettingScreen getSettingScreen(){
        return settingScreen;
    }

    public HighscoreScreen getHighscoreScreen(){
        return highscoreScreen;
    }

    public EncyclopediaScreen getEncyclopediaScreen(){
        return encyclopediaScreen;
    }

    public HelpScreen getHelpScreen(){
        return helpScreen;
    }

    public void restartGame(){
        setScreen(getGameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        splashScreen.dispose();
        menuScreen.dispose();
        gameScreen.dispose();
    }
}
