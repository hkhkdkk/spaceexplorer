package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tekmob.spaceexplorer.SpaceExplorer;

/**
 * Created by Rahmat Rasyidi Hakim on 12/8/2014.
 */
public class SoundScreen extends BaseScreen {

    private static Table table;
    private static Skin skin;
    private static CheckBox checkBoxSound;
    private static CheckBox checkBoxMusic;
    private static Slider volumeSlider;


    public SoundScreen(SpaceExplorer s) {
        super(s);
        table = new Table();
        skin = new Skin(Gdx.files.internal("skin.json"));
    }


}
