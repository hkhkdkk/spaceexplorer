package com.tekmob.spaceexplorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Rahmat Rasyidi Hakim on 12/7/2014.
 */
public class GamePreferences {

    private static final String PREFS_NAME = "myPref";
    private static final String PREF_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREF_HOWTOPLAY_ENABLED = "how.enabled";

    public GamePreferences(){}

    protected Preferences getPrefs(){
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    public boolean isHowToPlayEnabled(){
        return getPrefs().getBoolean(PREF_HOWTOPLAY_ENABLED, true);
    }

    public void setHowToPlayEnabled(boolean how){
        getPrefs().putBoolean(PREF_HOWTOPLAY_ENABLED, how);
        getPrefs().flush();
    }
}
