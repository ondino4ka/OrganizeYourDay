package com.dreamteam.organizeyourday;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by AXE607 on 08.03.2016.
 */
public class SettingsActivity extends Activity {

    void OnCreate(Bundle settings) {
       super.onCreate(settings);

        setContentView(R.layout.settings);


        //Мой говнокод
        final Button buttonAbout = (Button) findViewById(R.id.buttonAbout);
        final TextView aboutText = (TextView) findViewById(R.id.textAbout);
/*
        buttonAbout.setOnClickListener(new View.OnClickListener() {
                                           public void OnClick(View v) {
                                               aboutText.setText("Это прога просто 10/10");
                                           }

                                       }
        );
*/
    }


    public void SetTextAbout()
    {
        final TextView aboutText = (TextView) findViewById(R.id.textAbout);
        aboutText.setText("Это прога просто 10/10");

    }

}
