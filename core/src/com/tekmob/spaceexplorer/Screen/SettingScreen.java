package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tekmob.spaceexplorer.Assets;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 11/23/2014.
 */
public class SettingScreen extends BaseScreen {

    private Table table;
    private Button how, sound, credit;
    private Label title;
    private Image backgorund;

    public SettingScreen(SpaceExplorer s){
        super(s);
        table = new Table();

        loadUI();
        createUI();
        setupHandler();

        stage.addActor(backgorund);
        stage.addActor(table);
    }

    private void setupHandler(){
        how.addListener(new ClickListener() {
        	
            @Override
			public void clicked(InputEvent event, float x, float y) {
//              spaceExplorer.setScreen(spaceExplorer.getHelpScreen());
			}
        });

        sound.addListener(new ClickListener() {
        	
            @Override
			public void clicked(InputEvent event, float x, float y) {
//              spaceExplorer.setScreen(spaceExplorer.getHelpScreen());
			}
        });

        credit.addListener(new ClickListener() {
        	
            @Override
			public void clicked(InputEvent event, float x, float y) {
//              spaceExplorer.setScreen(spaceExplorer.getHelpScreen());
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
