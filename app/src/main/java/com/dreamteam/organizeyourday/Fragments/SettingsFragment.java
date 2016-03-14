package com.dreamteam.organizeyourday.Fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.dreamteam.organizeyourday.R;

public class SettingsFragment extends PreferenceFragment{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }
}
