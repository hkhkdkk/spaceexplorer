package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tekmob.spaceexplorer.Assets;
import com.tekmob.spaceexplorer.Controller.PreferenceController;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 12/23/2014.
 */
public class Item1 extends BaseScreen {

    private Table table;
    private Label title;
    private Label distance;
    private Image backgorund;
    private Image back;
    private Image planet;
    private Skin skin;
    private TextArea desc;
    private ScrollPane pane;
    private PreferenceController prefCont;

    public Item1(SpaceExplorer s){
        super(s);
        table = new Table();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        skin.addRegions(Assets.gameAtlas);

        prefCont = new PreferenceController();
        loadUI();
        createUI();
        setupHandler();

        stage.addActor(backgorund);
        stage.addActor(table);
    }

    private void setupHandler(){
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                spaceExplorer.getScreenstack().pop();
            }
        });
    }

    private void loadUI(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = Assets.nasa;
        l.fontColor = Color.WHITE;
        title = new Label(prefCont.getNameOfEncyclopedia("item0"), l);

        TextField.TextFieldStyle a = new TextField.TextFieldStyle();
        //a.background = skin.getDrawable("button");
        a.font = Assets.spaceHow;
        a.fontColor = Color.WHITE;

        desc = new TextArea(prefCont.getDescOfEncyclopedia("item0"), a);
        desc.setDisabled(true);
        desc.setFillParent(true);
        desc.setSize(400,400);

        pane = new ScrollPane(desc);
        pane.setFillParent(true);
        pane.layout();

        l.font = Assets.spaceHow;
        l.fontColor = Color.WHITE;

        distance = new Label(prefCont.getDistanceOfEncyclopedia("item0")+"",l);

        planet = new Image(Assets.loadTexture("item0.png"));
        back = new Image(Assets.arrow);

        backgorund = new Image(Assets.background);
        backgorund.setSize(SpaceExplorer.WIDTH, SpaceExplorer.HEIGHT);
    }

    private void createUI(){
        table.debug();

        Table table1 = new Table();
        table1.add(planet).row();
        table1.add(distance).row();

        table.setFillParent(true);
        table.top();
        table.add(title).padBottom(50).padTop(20).colspan(2).expand().center().row();
        table.add(table1).padLeft(20);
        table.add(desc).expand().padRight(20);
        table.row();
        table.add(back).padLeft(20).left();
    }

}