package com.dreamteam.organizeyourday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import com.dreamteam.organizeyourday.Fragments.SettingsFragment;



public class SettingsActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {


        if(MainActivity.index==2) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            int theme = sp.getInt("THEME", R.style.Pink);
            setTheme(theme);
        }


        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

    }






}
