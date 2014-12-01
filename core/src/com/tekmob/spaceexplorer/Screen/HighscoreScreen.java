package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tekmob.spaceexplorer.SpaceExplorer;

import java.security.Key;

/**
 * Created by Rahmat Rasyidi Hakim on 11/23/2014.
 */
public class HighscoreScreen extends BaseScreen {

    private Stage stage;
    private Table table;
    private Label title;
    private Skin skin;

    public HighscoreScreen(SpaceExplorer s){
        super(s);
        stage = new Stage();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        inputHandler();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void inputHandler(){
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            spaceExplorer.setScreen(spaceExplorer.getMenuScreen());
        }
    }
}
