package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tekmob.spaceexplorer.Assets;
import com.tekmob.spaceexplorer.SpaceExplorer;

import java.util.Locale;

/**
 * Created by Rahmat Rasyidi Hakim on 12/8/2014.
 */
public class SoundScreen extends BaseScreen {

    private Table table;
    private Skin skin;
    private CheckBox soundEffectsCheckbox;
    private CheckBox musicCheckbox;
    private Slider volumeSlider;
    private Label volumeValue, labelSound, labelMusic, title;
    private Image background, back;

    public SoundScreen(SpaceExplorer s) {
        super(s);
        table = new Table();

        skin = new Skin();
        skin.addRegions(Assets.utilAtlas);

        loadUI();
        createUI();
        setuphandler();

        stage.addActor(background);
        stage.addActor(table);

        /*
        // range is [0.0,1.0]; step is 0.1f
        volumeSlider = new Slider( 0f, 1f, 0.1f, false, skin );
        volumeSlider.setValue(spaceExplorer.getPreferences().getVolume());
        volumeSlider.addListener( new ChangeListener() {
            @Override
            public void changed(
                    ChangeEvent event,
                    Actor actor )
            {
                float value = ( (Slider) actor ).getValue();
                spaceExplorer.getPreferences().setVolume( value );
                updateVolumeLabel();
            }
        } );

        // create the volume label
        volumeValue = new Label( "", skin);
        updateVolumeLabel();

        // add the volume row
        table.row();
        table.add( "Volume" );
        table.add( volumeSlider );
        table.add( volumeValue ).width( 40 );*/
    }

    private void loadUI(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = Assets.nasa;
        l.fontColor = Color.WHITE;
        title = new Label("SOUND SETTING", l);

        l.font = Assets.space;
        l.fontColor = Color.WHITE;
        labelSound = new Label("Sound", l);

        labelMusic = new Label("Music", l);

        CheckBox.CheckBoxStyle c = new CheckBox.CheckBoxStyle();
        c.font = Assets.spaceHow;
        c.fontColor = Color.WHITE;
        c.checkboxOn = skin.getDrawable("grey_boxCheckmark");
        c.checkboxOff = skin.getDrawable("grey_boxCross");

        soundEffectsCheckbox = new CheckBox("",c);
        musicCheckbox = new CheckBox("",c);

        back = new Image(Assets.arrow);
        background = new Image(Assets.background);
        background.setSize(SpaceExplorer.WIDTH, SpaceExplorer.HEIGHT);

    }

    private void createUI(){
        table.setFillParent(true);
        table.top();
        table.add(title).padBottom(50).padTop(20).expandX().colspan(2).row();
        table.add(labelSound).padBottom(30);
        table.add(soundEffectsCheckbox).padBottom(30).row();

        table.add(labelMusic);
        table.add(musicCheckbox).row();
        table.add(back).padTop(200).left();
    }

    private void setuphandler(){
        soundEffectsCheckbox.setChecked(spaceExplorer.getPreferences().isSoundEnabled());
        soundEffectsCheckbox.addListener( new ChangeListener() {
            @Override
            public void changed(
                    ChangeEvent event,
                    Actor actor )
            {
                boolean enabled = soundEffectsCheckbox.isChecked();
                spaceExplorer.getPreferences().setSoundEnabled(enabled);
            }
        } );

        musicCheckbox.setChecked(spaceExplorer.getPreferences().isMusicEnabled());
        musicCheckbox.addListener( new ChangeListener() {
            @Override
            public void changed(
                    ChangeEvent event,
                    Actor actor )
            {
                boolean enabled = musicCheckbox.isChecked();
                spaceExplorer.getPreferences().setMusicEnabled(enabled);

                Assets.menuMusic.setLooping(true);
                Assets.menuMusic.play();
            }
        } );

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                spaceExplorer.getScreenstack().pop();
            }
        });
    }

    private void updateVolumeLabel() {
        float volume = ( spaceExplorer.getPreferences().getVolume() * 100 );
        volumeValue.setText( String.format( Locale.US, "%1.0f%%", volume ) );
    }

    @Override
    public void dispose() {
        skin.dispose();
        super.dispose();
    }
}
