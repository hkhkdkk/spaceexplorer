package com.tekmob.spaceexplorers.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tekmob.spaceexplorers.Assets;
import com.tekmob.spaceexplorers.Controller.PreferenceController;
import com.tekmob.spaceexplorers.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 12/23/2014.
 */
public class Item10 extends BaseScreen {

    private Table table;
    private Label title;
    private Label distance;
    private Image backgorund;
    private Image back;
    private Image planet;
    private TextArea desc;
    private PreferenceController prefCont;

    public Item10(SpaceExplorer s){
        super(s);
        table = new Table();

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
        title = new Label(prefCont.getNameOfEncyclopedia("item9"), l);

        TextField.TextFieldStyle a = new TextField.TextFieldStyle();
        a.font = Assets.roboto;
        a.fontColor = Color.WHITE;

        desc = new TextArea(prefCont.getDescOfEncyclopedia("item9"), a);
        desc.setX(300);
        desc.setY(600);
        desc.setScaleY(200);
        desc.setSize(850,400);
        desc.setPrefRows(8);
        desc.setDisabled(true);
        desc.layout();

        l.font = Assets.roboto;
        l.fontColor = Color.WHITE;

        distance = new Label(prefCont.getDistanceOfEncyclopedia("item9")+" AU",l);

        planet = new Image(Assets.loadTexture("item9.png"));
        back = new Image(Assets.arrow);

        backgorund = new Image(Assets.background);
        backgorund.setSize(SpaceExplorer.WIDTH, SpaceExplorer.HEIGHT);
    }

    private void createUI(){
        Table table1 = new Table();
        table1.add(planet).row();
        table1.add(distance).row();
        table1.add(Assets.fromEarth());

        table.setFillParent(true);
        table.top();
        table.add(title).padBottom(50).padTop(20).colspan(2).expand().center().row();
        table.add(table1).padLeft(20);

        table.add(desc).expand().size(900, 400).padRight(20);
        table.row();
        table.add(back).padLeft(20).left();
    }

}