package com.tekmob.spaceexplorer.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Muhammad Fajar on 23/12/2014.
 */
public class PreferenceController {
    private Preferences statistic;
    private Preferences achievement;
    private Preferences encyclopedia;
    private Preferences firstTime;

    final public static String STATISTIC = "Statistic";
    final public static String ACHIEVEMENT = "Achievement";
    final public static String ENCYCLOPEDIA = "Encyclopedia";
    final public static String ITEM = "item";

    public static final String PLAYER_ID = "player_id";
    public static final String DISTANCE = "longest_distance";
    public static final String SCORE = "highest_score";
    public static final String MISSILE = "most_missile";
    public static final String SHIELD = "most_shield";
    public static final String OBJECT = "object_discovered";
    public static final String GAMES = "total_games";
    public static final String MILESTONE = "longest_milestone";

    public PreferenceController() {
        statistic = Gdx.app.getPreferences(STATISTIC);
        achievement = Gdx.app.getPreferences(ACHIEVEMENT);
        encyclopedia = Gdx.app.getPreferences(ENCYCLOPEDIA);
        firstTime = Gdx.app.getPreferences("firstRun");
        if (firstTime.getBoolean("first", true)) {
            initStatistic();
            initAchievement();
            initEncyclopedia();
            // mark it as non-first run anymore
            firstTime.putBoolean("first", false);
            firstTime.flush();
            Gdx.app.log("MESSAGE","Data inisialized for the first time.");
        } else {
            Gdx.app.log("MESSAGE","Data loaded from previous.");
        }

    }

    private void initStatistic() {
        statistic.putInteger(PLAYER_ID, 1);
        statistic.putInteger(DISTANCE, 0);
        statistic.putInteger(SCORE, 0);
        statistic.putInteger(MISSILE, 0);
        statistic.putInteger(SHIELD, 0);
        statistic.putInteger(OBJECT, 0);
        statistic.putInteger(GAMES, 0);
        statistic.putString(MILESTONE, "Earth");
        statistic.flush();
    }

    private void initAchievement() {
        // Earth is already unlocked at first
        achievement.putBoolean(ITEM + "0", true);
        for (int i = 1; i < 10; i++) {
            // set others as locked
            achievement.putBoolean(ITEM + i, false);
        }
        achievement.flush();
    }

    private void initEncyclopedia() {
        FileHandle file = Gdx.files.internal("encyclopedia.txt");
        String isi = file.readString();
        Gdx.app.log("MESSAGE", isi); // for debug only, check whether file is loaded properly

        String [] temp = isi.split("--");
        for (int i = 0; i < temp.length; i++) {
            encyclopedia.putString(ITEM + i, temp[i]);
        }
        encyclopedia.flush();
    }


    public void putData(String prefName, String key, String data) {
        if (prefName.equalsIgnoreCase(STATISTIC)) {
            statistic.putString(key, data);
            statistic.flush();
        }
    }

    public void putData(String prefName, String key, int data) {
        if (prefName.equalsIgnoreCase(STATISTIC)) {
            statistic.putInteger(key, data);
            statistic.flush();
        }
    }

    public String getString(String prefName, String key) {
        if (prefName.equalsIgnoreCase(STATISTIC)) {
            return statistic.getString(key, "Data not found.");
        }
        return null;
    }

    public int getInteger(String prefName, String key) {
        if (prefName.equalsIgnoreCase(STATISTIC)) {
            return statistic.getInteger(key, 0);
        }
        return 0;
    }

    public void unlockAchievement(String item) {
        achievement.putBoolean(item, true);
        achievement.flush();
    }

    public boolean getAchievementStatus(String item) {
        return achievement.getBoolean(item);
    }


    public String [] getInfoForEncyclopedia(String itemName) {
        String temp = encyclopedia.getString(itemName);
        String [] content = temp.split(";");
        return content;
    }

    public String getTypeOfEncyclopedia(String itemName) {
        String [] temp = getInfoForEncyclopedia(itemName);
        return temp[0];
    }

    public String getNameOfEncyclopedia(String itemName) {
        String [] temp = getInfoForEncyclopedia(itemName);
        return temp[1];
    }

    public double getDistanceOfEncyclopedia(String itemName) {
        String [] temp = getInfoForEncyclopedia(itemName);
        return Double.parseDouble(temp[2]);
    }

    public String getDescOfEncyclopedia(String itemName) {
        String [] temp = getInfoForEncyclopedia(itemName);
        return temp[3];
    }
}
