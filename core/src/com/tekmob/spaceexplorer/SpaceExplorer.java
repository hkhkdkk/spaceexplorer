package com.tekmob.spaceexplorer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.tekmob.spaceexplorer.Screen.EncyclopediaScreen;
import com.tekmob.spaceexplorer.Screen.GameScreen;
import com.tekmob.spaceexplorer.Screen.HelpScreen;
import com.tekmob.spaceexplorer.Screen.HighscoreScreen;
import com.tekmob.spaceexplorer.Screen.HowToPlay;
import com.tekmob.spaceexplorer.Screen.MenuScreen;
import com.tekmob.spaceexplorer.Screen.SettingScreen;
import com.tekmob.spaceexplorer.Screen.SplashScreen;

public class SpaceExplorer extends Game {

    private SplashScreen splashScreen;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private SettingScreen settingScreen;
    private HighscoreScreen highscoreScreen;
    private EncyclopediaScreen encyclopediaScreen;
    private HelpScreen helpScreen;
    private HowToPlay howToPlay;

    public static int WIDTH = 800, HEIGHT = 480;

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
        howToPlay = new HowToPlay(this);

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

    public HowToPlay getHowToPlay(){
        return howToPlay;
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
