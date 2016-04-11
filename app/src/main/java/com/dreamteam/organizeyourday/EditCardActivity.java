package com.dreamteam.organizeyourday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class EditCardActivity extends AppCompatActivity {

    private boolean isEnterAnimationComplete = false;
    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.down_anim_fade_in, R.anim.down_anim_out);
    }
    @Override
    public void onBackPressed() {
        if(isEnterAnimationComplete) {
            super.onBackPressed();
        }
    }

    public void onEnterAnimationComplete()
    {
        isEnterAnimationComplete = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ThemeManager.setCurrentNoActionBarTheme(this);
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_edit_card);
        setContentView(R.layout.activity_edit_card_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        TextView titleText = (TextView) findViewById(R.id.textAbout);
        titleText.setText(intent.getStringExtra("title"));
    }

}
