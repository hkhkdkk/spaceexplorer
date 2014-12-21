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
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 12/8/2014.
 */
public class PlanetScreen extends BaseScreen {

    private Table table;
    private Label title, label1, label2;
    private Image backgorund;
    private Image back;
    private Image planet;
    private Skin skin;
    private TextArea desc;
    private ScrollPane pane;

    public PlanetScreen(SpaceExplorer s){
        super(s);
        table = new Table();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        skin.addRegions(Assets.gameAtlas);

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
        title = new Label("MARS", l);

        TextField.TextFieldStyle a = new TextField.TextFieldStyle();
        //a.background = skin.getDrawable("button");
        a.font = Assets.spaceHow;
        a.fontColor = Color.WHITE;

        desc = new TextArea(
                "LOREEEEEEEEEEEM IPSUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM\n" +
                "LOREEEEEEM IPSUUUUUM", a);

        desc.setDisabled(true);
        pane = new ScrollPane(desc,skin);

        l.font = Assets.spaceHow;
        l.fontColor = Color.WHITE;

        planet = new Image(Assets.dummy);
        label1 = new Label("0.6 AU", l);
        label2 = new Label("FROM EARTH", l);
        back = new Image(Assets.arrow);

        backgorund = new Image(Assets.background);
        backgorund.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void createUI(){
        table.debug();

        Table table1 = new Table();
        table1.add(planet).row();
        table1.add(label1).row();
        table1.add(label2);

        table.setFillParent(true);
        table.top();
        table.add(title).padBottom(50).padTop(20).colspan(2).expand().center().row();
        table.add(table1).padLeft(20);
        table.add(pane).expandY().padRight(20);
        table.row();
        table.add(back).padLeft(20).left();
    }

}
