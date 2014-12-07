package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 11/23/2014.
 */
public class EncyclopediaScreen extends BaseScreen {

    private Stage stage;
    private Label title;

    public EncyclopediaScreen(SpaceExplorer s){
        super(s);

        Gdx.input.setCatchBackKey(true);
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void render(float delta) {
        super.render(delta);

    }

    @Override
    public void show() {
        stage.getViewport().setScreenSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /*private void inputHandler(){
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            spaceExplorer.setScreen(spaceExplorer.getMenuScreen());
        }
    }*/

}
