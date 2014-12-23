package com.tekmob.spaceexplorer.android;

import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tekmob.spaceexplorer.Controller.PreferenceController;
import com.tekmob.spaceexplorer.SpaceExplorer;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = true;
        config.useCompass = true;
		initialize(new SpaceExplorer(), config);

        // test preferences
        PreferenceController preferenceController = new PreferenceController();
	}
}
