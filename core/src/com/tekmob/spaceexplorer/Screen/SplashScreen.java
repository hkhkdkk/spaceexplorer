package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tekmob.spaceexplorer.Assets;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 11/20/2014.
 */
public class SplashScreen extends BaseScreen {

    private Texture texture;
    private Image splashImage;
    private Stage stage;
    private boolean animationDone = false;

    public SplashScreen(SpaceExplorer s){
        super(s);

        stage = new Stage();
        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        splashImage = new Image(texture);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();

        if(animationDone){
            spaceExplorer.setScreen(spaceExplorer.getMenuScreen());
        }
    }

    @Override
    public void show() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        splashImage.setSize(w,h);
        stage.addActor(splashImage);
        splashImage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(0.75f),Actions.delay(1.5f),Actions.run(new Runnable() {
            @Override
            public void run() {

                animationDone = true;
            }
        })));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
