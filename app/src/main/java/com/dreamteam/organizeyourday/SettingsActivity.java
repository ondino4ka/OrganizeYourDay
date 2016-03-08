package com.dreamteam.organizeyourday;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by AXE607 on 08.03.2016.
 */
public class SettingsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

    }


    public void setTextAbout()
    {
        final TextView aboutText = (TextView) findViewById(R.id.textAbout);
        aboutText.setText("Это прога просто 10/10");

    }

}
