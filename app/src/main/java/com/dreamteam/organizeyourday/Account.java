package com.dreamteam.organizeyourday;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.dreamteam.organizeyourday.Fragments.SettingsFragment;

/**
 * Created by kostya on 13.03.2016.
 */
public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(MainActivity.index==2) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            int theme = sp.getInt("THEME", R.style.Pink);
            setTheme(theme);
        }

        super.onCreate(savedInstanceState);

    }
}
