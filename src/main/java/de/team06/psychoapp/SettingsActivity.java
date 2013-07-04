package de.team06.psychoapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Kevin on 03.07.13. It´s so fucking awesome.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
