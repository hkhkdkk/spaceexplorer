package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tekmob.spaceexplorer.Assets;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 11/23/2014.
 */
public class SettingScreen extends BaseScreen {

    private Skin skin;
    private Stage stage;
    private Table table;
    private Button how, sound, credit;
    private Label title;
    private Image backgorund;
    private boolean back = true;

    public SettingScreen(SpaceExplorer s){
        super(s);
        stage = new Stage(new ScreenViewport());
        skin = new Skin();
        table = new Table();


        loadUI();
        createUI();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
        inputHandler();
        if(!back){
            back = true;
//            spaceExplorer.setScreen(spaceExplorer.getMenuScreen());
        }
    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(backgorund);
        stage.addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void inputHandler(){
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            back = false;
        }

        how.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                spaceExplorer.setScreen(spaceExplorer.getHelpScreen());
            }
        });

        sound.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        credit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
    }

    private void loadUI(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = Assets.nasa;
        l.fontColor = Color.WHITE;
        title = new Label("SETTING", l);

        TextButton.TextButtonStyle h = new TextButton.TextButtonStyle();
        h.font = Assets.space;
        h.fontColor = Color.WHITE;

        how = new TextButton("HOW TO PLAY", h);
        sound = new TextButton("SOUND", h);
        credit = new TextButton("CREDITS", h);

        backgorund = new Image(Assets.background);
        backgorund.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void createUI(){
        table.debug();
        table.setFillParent(true);
        table.top();
        table.add(title).padBottom(50).padTop(20).row();
        table.add(how).padBottom(20).padRight(50).padLeft(50).expand().row();
        table.add(sound).padBottom(20).expand().row();
        table.add(credit).padBottom(40).expand().row();
        table.pack();
    }
}
