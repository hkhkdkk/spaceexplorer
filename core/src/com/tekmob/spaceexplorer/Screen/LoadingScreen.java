package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tekmob.spaceexplorer.LoadingBar;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 11/23/2014.
 */
public class LoadingScreen extends BaseScreen {

    private Stage stage;
    private Image logo;
    private Image loadingFrame;
    private Image loadingBarHidden;
    private Image screenBg;
    private Image loadingBg;

    private TextureAtlas atlas;
    private float startX, endX;
    private float percent;
    private Actor loadingBar;

    public LoadingScreen(SpaceExplorer s){
        super(s);
    }
/*
    @Override
    public void show() {
        spaceExplorer.manager.load("ui/loading.pack", TextureAtlas.class);
        spaceExplorer.manager.finishLoading();

        stage = new Stage(new ScreenViewport());
        atlas = spaceExplorer.manager.get("ui/loading.pack", TextureAtlas.class);

        logo = new Image(atlas.findRegion("libgdx-logo"));
        loadingFrame = new Image(atlas.findRegion("loading-frame"));
        loadingBarHidden = new Image(atlas.findRegion("loading-bar-hidden"));
        screenBg = new Image(atlas.findRegion("screen-bg"));
        loadingBg = new Image(atlas.findRegion("loading-frame-bg"));

        // Add the loading bar animation
        Animation anim = new Animation(0.05f, atlas.findRegions("loading-bar-anim") );
        anim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
        loadingBar = new LoadingBar(anim);

        // Or if you only need a static bar, you can do
        // loadingBar = new Image(atlas.findRegion("loading-bar1"));

        // Add all the actors to the stage
        stage.addActor(screenBg);
        stage.addActor(loadingBar);
        stage.addActor(loadingBg);
        stage.addActor(loadingBarHidden);
        stage.addActor(loadingFrame);
        stage.addActor(logo);

        FileHandleResolver resolver = new InternalFileHandleResolver();
        spaceExplorer.manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        spaceExplorer.manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        // load to fonts via the generator (implicitely done by the FreetypeFontLoader).
        // Note: you MUST specify a FreetypeFontGenerator defining the ttf font file name and the size
        // of the font to be generated. The names of the fonts are arbitrary and are not pointing
        // to a file on disk!
        FreetypeFontLoader.FreeTypeFontLoaderParameter size1Params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        size1Params.fontFileName = "ui/nasalization.ttf";
        size1Params.fontParameters.size = 10;

        // Add everything to be loaded, for instance:
        spaceExplorer.manager.load("size10.ttf", BitmapFont.class, size1Params);
        spaceExplorer.manager.load("ui/menu.pack", TextureAtlas.class);
    }

    @Override
    public void resize(int width, int height) {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        stage.getViewport().setScreenSize(width, height);
        //stage.setViewport();
        //stage.setViewport(width , height, false);

        // Make the background fill the screen
        screenBg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Place the logo in the middle of the screen and 100 px up
        logo.setX((width - logo.getWidth()) / 2);
        logo.setY((height - logo.getHeight()) / 2 + 100);

        // Place the loading frame in the middle of the screen
        loadingFrame.setX((stage.getWidth() - loadingFrame.getWidth()) / 2);
        loadingFrame.setY((stage.getHeight() - loadingFrame.getHeight()) / 2);

        // Place the loading bar at the same spot as the frame, adjusted a few px
        loadingBar.setX(loadingFrame.getX() + 15);
        loadingBar.setY(loadingFrame.getY() + 5);

        // Place the image that will hide the bar on top of the bar, adjusted a few px
        loadingBarHidden.setX(loadingBar.getX() + 35);
        loadingBarHidden.setY(loadingBar.getY() - 3);
        // The start position and how far to move the hidden loading bar
        startX = loadingBarHidden.getX();
        endX = 440;

        // The rest of the hidden bar
        loadingBg.setSize(450, 50);
        loadingBg.setX(loadingBarHidden.getX() + 30);
        loadingBg.setY(loadingBarHidden.getY() + 3);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (spaceExplorer.manager.update()) {
            if(Gdx.input.justTouched())
            spaceExplorer.setScreen(spaceExplorer.getMenuScreen());
        }

        // Interpolate the percentage to make it more smooth
        percent = Interpolation.linear.apply(percent, spaceExplorer.manager.getProgress(), 0.1f);

        // Update positions (and size) to match the percentage
        loadingBarHidden.setX(startX + endX * percent);
        loadingBg.setX(loadingBarHidden.getX() + 30);
        loadingBg.setWidth(450 - 450 * percent);
        loadingBg.invalidate();

        // Show the loading screen
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        spaceExplorer.manager.unload("ui/loading.pack");
    }

    @Override
    public void dispose() {
        super.dispose();
        atlas.dispose();
        stage.dispose();
    }*/
}
