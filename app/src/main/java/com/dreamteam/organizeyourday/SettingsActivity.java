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

        seCurrentTheme();
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .add(android.R.id.content,new SettingsFragment())
                .commit();

    }

    private void seCurrentTheme()
    {
        int theme;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        switch(MainActivity.index)
        {
            case 0:
                theme = sp.getInt("THEME", R.style.AppTheme);
                setTheme(theme);
                break;
            case 1:
                break;
            case 2:
                theme = sp.getInt("THEME", R.style.Pink);
                setTheme(theme);
                break;
            default:
                break;
        }
    }




}
