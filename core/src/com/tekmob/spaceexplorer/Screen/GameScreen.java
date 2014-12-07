package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 11/20/2014.
 */
public class GameScreen extends BaseScreen {

    public GameScreen(SpaceExplorer s){
        super(s);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        inputHandler();
    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
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
