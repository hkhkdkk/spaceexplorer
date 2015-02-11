package com.tekmob.spaceexplorers.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tekmob.spaceexplorers.Assets;
import com.tekmob.spaceexplorers.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 11/20/2014.
 */

public class MenuScreen extends BaseScreen {

    private Skin skin;
    private Table table;
    private Button playButton, highscoreButton, encyclopediaButton, settingButton;
    private Label title;
    private Image background;

    public MenuScreen(SpaceExplorer s) {
        super(s);

        table = new Table();

        skin = new Skin();
        skin.addRegions(Assets.menuAtlas);

        playMusic();
        loadUI();
        createUI();
        setuphandler();

        stage.addActor(background);
        stage.addActor(table);
    }

	@Override
    public void dispose() {
        skin.dispose();
        super.dispose();
    }

    private void playMusic(){
        Gdx.app.log("MUSIC ENABLED", spaceExplorer.getPrefController().isMusicEnabled()+"");
        if(spaceExplorer.getPrefController().isMusicEnabled()){
            Assets.gameMusic.setLooping(true);
            Assets.gameMusic.play();
        }
    }

    private void setuphandler() {
    	playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                spaceExplorer.getScreenstack().push(new GameScreen(spaceExplorer));
            }
    	});
    	
    	settingButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
            	spaceExplorer.getScreenstack().push(new SettingScreen(spaceExplorer));
			}
        });

        highscoreButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
            	spaceExplorer.getScreenstack().push(new StatScreen(spaceExplorer));
			}
       	});

        encyclopediaButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
            	spaceExplorer.getScreenstack().push(new EncyclopediaScreen(spaceExplorer));
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
        background.setSize(SpaceExplorer.WIDTH, SpaceExplorer.HEIGHT);
        
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
