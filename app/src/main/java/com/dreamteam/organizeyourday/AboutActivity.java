package com.dreamteam.organizeyourday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity{

    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

    }

    public void setTextAbout(View view) {

        TextView aboutText = (TextView)findViewById(R.id.textAbout);
        RelativeLayout relativeLayout =(RelativeLayout) findViewById(R.id.aboutLayout);

        aboutText.setText("Это прога просто " + count + "/10");
        if(count>=10)
        {
            relativeLayout.setBackgroundResource(R.drawable.hack_0);
        }
        count++;
    }
}
