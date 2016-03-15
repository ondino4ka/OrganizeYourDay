package com.dreamteam.organizeyourday;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class AboutActivity extends AppCompatActivity {


    private byte count = 0;
    String rep_url = "https://github.com/mkinitcpio/OrganizeYourDay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);


    }

    public void jumpToRepository(View view) {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(rep_url));
        startActivity(browser);
    }


}




