package com.dreamteam.organizeyourday;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity{

    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);



    }

    public void setTextAbout(View view) {

        TextView aboutText = (TextView)findViewById(R.id.textAbout);
       RelativeLayout relativeLayout =(RelativeLayout) findViewById(R.id.settingsLayout);

        aboutText.setText("Это прога просто " + count + "/10");
        if(count>=10)
        {
            relativeLayout.setBackgroundResource(R.drawable.hack_0);
        }
        count++;
    }

}
