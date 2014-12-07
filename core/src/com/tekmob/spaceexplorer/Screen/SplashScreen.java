package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 11/20/2014.
 */
public class SplashScreen extends BaseScreen {

    private Texture texture;
    private Image splashImage;

    public SplashScreen(SpaceExplorer s){
        super(s);
        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        splashImage = new Image(texture);
        splashImage.setSize(SpaceExplorer.WIDTH, SpaceExplorer.HEIGHT);
        stage.addActor(splashImage);
    }

    @Override
    public void show() {
    	super.show();
        splashImage.addAction(Actions.sequence(
    		Actions.alpha(0),
    		Actions.fadeIn(0.75f),
    		Actions.delay(1.5f),
    		Actions.fadeOut(0.75f),
    		Actions.alpha(0),
    		Actions.run(new Runnable() {
	            @Override
	            public void run() {
	                spaceExplorer.getScreenstack().changeScreen(new MenuScreen(spaceExplorer));
	            }
            }
		)));
    }

    @Override
    public void dispose() {
        texture.dispose();
        super.dispose();
    }

    
}
