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

        ThemeManager.setCurrentTheme(this);
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .add(android.R.id.content,new SettingsFragment())
                .commit();

    }

    public void jumpToMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }






}
