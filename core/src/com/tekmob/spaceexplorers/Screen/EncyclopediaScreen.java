package com.tekmob.spaceexplorers.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tekmob.spaceexplorers.Assets;
import com.tekmob.spaceexplorers.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 11/23/2014.
 */

public class EncyclopediaScreen extends BaseScreen {
    private Table table;
    private Table container;
    private Label title;
    private Image backgorund;
    private Image back;
    private ScrollPane sc;
    private SpriteBatch batch = new SpriteBatch();

    private Image item1;
    private Image item2;
    private Image item3;
    private Image item4;
    private Image item5;
    private Image item6;
    private Image item7;
    private Image item8;
    private Image item9;
    private Image item10;

    public EncyclopediaScreen(SpaceExplorer s){
        super(s);
        table = new Table();
        container = new Table();

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

        item1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //cek dulu disini bisa gak dibuka?
                if(spaceExplorer.getPrefController().getAchievementStatus("item0")){
                    if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                    spaceExplorer.getScreenstack().push(new Item1(spaceExplorer));
                }
            }
        });

        item2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().getAchievementStatus("item1")) {
                    if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                    spaceExplorer.getScreenstack().push(new Item2(spaceExplorer));
                }
            }
        });

        item3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().getAchievementStatus("item2")) {

                    if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                    spaceExplorer.getScreenstack().push(new Item3(spaceExplorer));
                }
            }
        });

        item4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().getAchievementStatus("item3")) {

                    if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                    spaceExplorer.getScreenstack().push(new Item4(spaceExplorer));
                }
            }
        });

        item5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().getAchievementStatus("item4")) {

                    if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                    spaceExplorer.getScreenstack().push(new Item5(spaceExplorer));
                }
            }
        });

        item6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().getAchievementStatus("item5")) {

                    if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                    spaceExplorer.getScreenstack().push(new Item6(spaceExplorer));
                }
            }
        });

        item7.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().getAchievementStatus("item6")) {

                    if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                    spaceExplorer.getScreenstack().push(new Item7(spaceExplorer));
                }
            }
        });

        item8.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().getAchievementStatus("item7")) {

                    if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                    spaceExplorer.getScreenstack().push(new Item8(spaceExplorer));
                }
            }
        });

        item9.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().getAchievementStatus("item8")) {

                    if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                    spaceExplorer.getScreenstack().push(new Item9(spaceExplorer));
                }
            }
        });

        item10.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(spaceExplorer.getPrefController().getAchievementStatus("item9")) {

                    if(spaceExplorer.getPrefController().isSoundEnabled()) Assets.click.play();
                    spaceExplorer.getScreenstack().push(new Item10(spaceExplorer));
                }
            }
        });
    }

    private void loadUI(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = Assets.nasa;
        l.fontColor = Color.WHITE;
        title = new Label("ENCYCLOPEDIA", l);

        if(spaceExplorer.getPrefController().getAchievementStatus("item0")){
            item1 = new Image(Assets.loadTexture("item0.png"));
        } else {
            item1 = new Image(Assets.loadTexture("lock.png"));
        }

        if(spaceExplorer.getPrefController().getAchievementStatus("item1")){
            item2 = new Image(Assets.loadTexture("item1.png"));
        } else {
            item2 = new Image(Assets.loadTexture("lock.png"));
        }

        if(spaceExplorer.getPrefController().getAchievementStatus("item2")){
            item3 = new Image(Assets.loadTexture("item2.png"));
        } else {
            item3 = new Image(Assets.loadTexture("lock.png"));
        }

        if(spaceExplorer.getPrefController().getAchievementStatus("item3")){
            item4 = new Image(Assets.loadTexture("item3.png"));
        } else {
            item4 = new Image(Assets.loadTexture("lock.png"));
        }

        if(spaceExplorer.getPrefController().getAchievementStatus("item4")){
            item5 = new Image(Assets.loadTexture("item4.png"));
        } else {
            item5 = new Image(Assets.loadTexture("lock.png"));
        }

        if(spaceExplorer.getPrefController().getAchievementStatus("item5")){
            item6 = new Image(Assets.loadTexture("item5.png"));
        } else {
            item6 = new Image(Assets.loadTexture("lock.png"));
        }

        if(spaceExplorer.getPrefController().getAchievementStatus("item6")){
            item7 = new Image(Assets.loadTexture("item6.png"));
        } else {
            item7 = new Image(Assets.loadTexture("lock.png"));
        }

        if(spaceExplorer.getPrefController().getAchievementStatus("item7")){
            item8 = new Image(Assets.loadTexture("item7.png"));
        } else {
            item8 = new Image(Assets.loadTexture("lock.png"));
        }

        if(spaceExplorer.getPrefController().getAchievementStatus("item8")){
            item9 = new Image(Assets.loadTexture("item8.png"));
        } else {
            item9 = new Image(Assets.loadTexture("lock.png"));
        }

        if(spaceExplorer.getPrefController().getAchievementStatus("item9")){
            item10 = new Image(Assets.loadTexture("item9.png"));
        } else {
            item10 = new Image(Assets.loadTexture("lock.png"));
        }

        back = new Image(Assets.arrow);
        backgorund = new Image(Assets.background);
        backgorund.setSize(SpaceExplorer.WIDTH, SpaceExplorer.HEIGHT);
    }


    private void createUI(){
        table.setFillParent(true);
        table.top();
        container.add();
        container.add(item1);
        container.add(item2);
        container.add(item3);
        container.add(item4);
        container.add(item5);
        container.add(item6);
        container.add(item7);
        container.add(item8);
        container.add(item9);
        container.add(item10);

        sc = new ScrollPane(container);
        sc.layout();
        table.add(title).padBottom(50).padTop(20).expandX().row();
        table.add(sc).expand().padTop(100).padBottom(30).row();
        table.add(back).left();
    }
}
