package com.tekmob.spaceexplorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Rahmat Rasyidi Hakim on 12/7/2014.
 */
public class GamePreferences {

    private static final String PREFS_NAME = "myPrefs";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREF_HOWTOPLAY_ENABLED = "how.enabled";

    protected Preferences getPrefs(){
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    public GamePreferences(){
        init();
    }

    private void init(){
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, true);
        getPrefs().putBoolean(PREF_SOUND_ENABLED, true);
        getPrefs().putBoolean(PREF_HOWTOPLAY_ENABLED, true);
        getPrefs().flush();
    }


    public boolean isHowToPlayEnabled(){
        return getPrefs().getBoolean(PREF_HOWTOPLAY_ENABLED);
    }

    public boolean isSoundEnabled(){
        return getPrefs().getBoolean(PREF_SOUND_ENABLED);
    }

    public void setSoundEnabled(boolean how){
        getPrefs().putBoolean(PREF_SOUND_ENABLED, how);
        getPrefs().flush();
    }


    public boolean isMusicEnabled(){
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED);
    }

    public void setMusicEnabled(boolean how){
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, how);
        getPrefs().flush();
    }

    public void setHowToPlayEnabled(boolean how){
        getPrefs().putBoolean(PREF_HOWTOPLAY_ENABLED, how);
        getPrefs().flush();
    }
}
