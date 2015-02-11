package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tekmob.spaceexplorer.Assets;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 12/8/2014.
 */
public class AboutScreen extends BaseScreen {

    private Table table;
    private Label title;
    private Image backgorund;
    private Image back;

    private TextArea creditTextArea;

    public AboutScreen(SpaceExplorer s){
        super(s);
        table = new Table();

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
                if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                spaceExplorer.getScreenstack().pop();
            }
        });
    }

    private void loadUI(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = Assets.nasa;
        l.fontColor = Color.WHITE;
        title = new Label("ABOUT", l);

        TextField.TextFieldStyle a = new TextField.TextFieldStyle();
        a.font = Assets.roboto;
        a.fontColor = Color.WHITE;

        creditTextArea = new TextArea(Assets.about, a);
        creditTextArea.setDisabled(true);
        creditTextArea.setScaleY(200);
        creditTextArea.setPrefRows(20);
        creditTextArea.setSize(spaceExplorer.WIDTH-20,400);

        creditTextArea.layout();

        back = new Image(Assets.arrow);
        backgorund = new Image(Assets.background);
        backgorund.setSize(SpaceExplorer.WIDTH, SpaceExplorer.HEIGHT);
    }


    private void createUI(){
        table.setFillParent(true);
        table.top();
        table.add(title).padBottom(50).padTop(20).expandX().row();
        table.add(creditTextArea).padLeft(80).size(spaceExplorer.WIDTH-20,400).expand().row();
        table.add(back).left();
    }
}

