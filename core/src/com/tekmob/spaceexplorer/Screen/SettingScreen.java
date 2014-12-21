package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    private Image back;
    private Skin skin;

    public SettingScreen(SpaceExplorer s){
        super(s);
        table = new Table();
        skin = new Skin();
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

        how.addListener(new ClickListener() {
            @Override
			public void clicked(InputEvent event, float x, float y) {
              spaceExplorer.getScreenstack().push(new HelpScreen(spaceExplorer));
			}
        });

        sound.addListener(new ClickListener() {
            @Override
			public void clicked(InputEvent event, float x, float y) {
              spaceExplorer.getScreenstack().push(new SoundScreen(spaceExplorer));
			}
        });

        credit.addListener(new ClickListener() {
            @Override
			public void clicked(InputEvent event, float x, float y) {
                spaceExplorer.getScreenstack().push(new CreditScreen(spaceExplorer));
			}
        });
    }

    private void loadUI(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = Assets.nasa;
        l.fontColor = Color.WHITE;
        title = new Label("SETTING", l);

        ImageTextButton.ImageTextButtonStyle h = new ImageTextButton.ImageTextButtonStyle();
        h.font = Assets.space;
        h.fontColor = Color.WHITE;
        h.up = skin.getDrawable("button");
        h.down = skin.getDrawable("button");

        how = new ImageTextButton("HOW TO PLAY", h);
        sound = new ImageTextButton("SOUND", h);
        credit = new ImageTextButton("CREDITS", h);

        back = new Image(Assets.arrow);

        backgorund = new Image(Assets.background);
        backgorund.setSize(SpaceExplorer.WIDTH, SpaceExplorer.HEIGHT);

    }

    private void createUI(){
        table.setFillParent(true);
        table.top();
        table.add(title).padBottom(50).padTop(20).expandX().row();
        table.add(how).size(800,100).padBottom(20).row();
        table.add(sound).size(800,100).padBottom(20).row();
        table.add(credit).size(800,100).padBottom(40).row();
        table.add(back).left();
    }
}
