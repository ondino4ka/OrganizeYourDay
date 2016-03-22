package com.dreamteam.organizeyourday;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.dreamteam.organizeyourday.Fragments.SettingsFragment;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.setCurrentTheme(this);
        super.onCreate(savedInstanceState);

    }


}
