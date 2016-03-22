package com.dreamteam.organizeyourday;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dreamteam.organizeyourday.Fragments.SettingsFragment;

public class EditCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ThemeManager.setCurrentTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
    }


}
