package com.dreamteam.organizeyourday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

    }

    public void setTextAbout(View view) {

        TextView aboutText = (TextView)findViewById(R.id.textAbout);
        aboutText.setText("Это прога просто 10/10");

    }

}
