package com.dreamteam.organizeyourday;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dreamteam.organizeyourday.Fragments.SettingsFragment;

public class EditCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        seCurrentTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
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
                theme = sp.getInt("THEME", R.style.Blue);
                setTheme(theme);
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
