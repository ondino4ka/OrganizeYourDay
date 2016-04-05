package com.dreamteam.organizeyourday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EditCardActivity extends AppCompatActivity {

    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.alpha_in, R.anim.scale_by_y_reverse);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ThemeManager.setCurrentTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
        Intent intent = getIntent();
        TextView titleText = (TextView) findViewById(R.id.textAbout);
        titleText.setText(intent.getStringExtra("title"));
    }

}
