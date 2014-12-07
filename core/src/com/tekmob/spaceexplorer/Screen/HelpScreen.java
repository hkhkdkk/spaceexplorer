package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tekmob.spaceexplorer.Assets;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 12/1/2014.
 */
public class HelpScreen extends BaseScreen {

    private Label title, help1, help2, help3, help4, help5;
    private Table table;
    private Stage stage;
    private Skin skin;
    private OrthographicCamera cam;
    private Image background, image1, image2, image3, image4, image5, image6, image7, image8;
    private boolean back = true;

    public HelpScreen(SpaceExplorer s) {
        super(s);
        cam = new OrthographicCamera(640,480);

        stage = new Stage();
        table = new Table();
        skin = new Skin();

        skin.addRegions(Assets.gameAtlas);
        loadUI();
        createUI();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();

        stage.act(delta);
        stage.draw();
        inputhandler();
        if(!back){
            back = true;
//            spaceExplorer.setScreen(spaceExplorer.getSettingScreen());
        }
    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
        stage.addActor(background);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }

    private void loadUI(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = Assets.nasa;
        l.fontColor = Color.WHITE;
        title = new Label("HOW TO PLAY", l);

        Label.LabelStyle k = new Label.LabelStyle();
        k.font = Assets.spaceHow;
        k.fontColor = Color.WHITE;
        help1 = new Label("TILT TO CONTROL", k);
        help2 = new Label("YOUR SHIP", k);
        help3 = new Label("AVOID THIS!", k);
        help4 = new Label("COLLECT THESE!", k);
        help5 = new Label("TOUCH TO USE", k);

        image1 = new Image(skin.getRegion("tilt"));
        image2 = new Image(skin.getRegion("ship"));
        image3 = new Image(skin.getRegion("meteorGrey_big1"));
        image4 = new Image(skin.getRegion("pushield"));
        image5 = new Image(skin.getRegion("pumissile"));
        image6 = new Image(skin.getRegion("shield"));
        image7 = new Image(skin.getRegion("missile"));
        image8 = new Image(Assets.arrow);

        background = new Image(Assets.background);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void createUI(){
        Table table1 = new Table();
        Table table2 = new Table();

        table.setFillParent(true);
        table.top();

        table.add(title).colspan(3).padTop(20).padBottom(50).center().row();
        table.add(image1);
        table.add(image2);
        table.add(image3);
        table.row().padTop(20);

        table.add(help1);
        table.add(help2).padLeft(30).padRight(30);
        table.add(help3);
        table.row().padTop(40);

        table1.add(image4).padLeft(10);
        table1.add(image5);
        table.add(table1).center();

        table2.add(image6);
        table2.add(image7).padLeft(10);
        table.add(table2).center();
        table.row().padTop(20);

        table.add(help4);
        table.add(help5).padLeft(30);
        table.row().padTop(40);

        table.add(image8).left();
    }

    private void inputhandler(){
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            back = false;
        }
    }
}
