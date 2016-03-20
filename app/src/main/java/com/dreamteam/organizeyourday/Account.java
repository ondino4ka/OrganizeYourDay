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
        seCurrentTheme();
        super.onCreate(savedInstanceState);

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
