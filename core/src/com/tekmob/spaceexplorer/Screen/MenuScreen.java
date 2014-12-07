package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tekmob.spaceexplorer.Assets;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 11/20/2014.
 */
public class MenuScreen extends BaseScreen {

    private Skin skin;
    private Stage stage;
    private Table table;
    private Button playButton, highscoreButton, encyclopediaButton, settingButton;
    private Label title;
    private Image background;
    private OrthographicCamera camera;

    public MenuScreen(SpaceExplorer s) {
        super(s);

        camera = new OrthographicCamera(640,480);

        table = new Table();
        stage = new Stage();

        skin = new Skin();
        skin.addRegions(Assets.menuAtlas);

        loadUI();
        createUI();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        Gdx.input.setInputProcessor(stage);
        stage.act(delta);
        stage.draw();
        inputhandler();
    }

    @Override
    public void show() {

        Gdx.input.setCatchBackKey(false);
        stage.addActor(background);
        stage.addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        skin.dispose();
    }

    private void inputhandler(){
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                spaceExplorer.setScreen(spaceExplorer.getHowToPlay());
            }
        });

        settingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                spaceExplorer.setScreen(spaceExplorer.getSettingScreen());
            }
        });

        highscoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                spaceExplorer.setScreen(spaceExplorer.getHighscoreScreen());
            }
        });

        encyclopediaButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                spaceExplorer.setScreen(spaceExplorer.getEncyclopediaScreen());
            }
        });
    }

    private void loadUI(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = Assets.nasa;
        l.fontColor = Color.WHITE;
        title = new Label("SPACE EXPLORER", l);

        ImageButtonStyle p = new ImageButtonStyle();
        p.imageDown = skin.getDrawable("playup");
        p.imageUp = skin.getDrawable("playdown");
        playButton = new ImageButton(p);

        ImageButtonStyle s = new ImageButtonStyle();
        s.imageDown = skin.getDrawable("settingup");
        s.imageUp = skin.getDrawable("settingdown");
        settingButton = new ImageButton(s);

        ImageButtonStyle h = new ImageButtonStyle();
        h.imageDown = skin.getDrawable("highscoreup");
        h.imageUp = skin.getDrawable("highscoredown");
        highscoreButton = new ImageButton(h);

        ImageButtonStyle e = new ImageButtonStyle();
        e.imageDown = skin.getDrawable("encyclopediaup");
        e.imageUp = skin.getDrawable("encyclopediadown");
        encyclopediaButton = new ImageButton(e);

        background = new Image(Assets.background);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void createUI(){
         table.setFillParent(true);
        table.top();
        table.add(title).colspan(3).center().padTop(20).padBottom(50).row();
        table.add(playButton).colspan(3).center().size(180).expand().row();
        table.add(settingButton).padRight(100).padLeft(50).size(180).expand();
        table.add(highscoreButton).pad(50).size(180).expand();
        table.add(encyclopediaButton).padRight(50).padLeft(100).size(180).expand();
        table.row().padBottom(50).expand();
        table.pack();
    }
}
