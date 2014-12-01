package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 11/20/2014.
 */
public class BaseScreen implements Screen {

    protected SpaceExplorer spaceExplorer;
    protected final Color bgColor;
    private FPSLogger fpsLogger;

    public BaseScreen(SpaceExplorer s){
        spaceExplorer = s;
        bgColor = new Color(0, 0, 0, 0);
        fpsLogger = new FPSLogger();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fpsLogger.log();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
