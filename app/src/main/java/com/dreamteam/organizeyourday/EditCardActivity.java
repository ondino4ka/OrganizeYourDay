package com.dreamteam.organizeyourday;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;

public class EditCardActivity extends AppCompatActivity {

    Intent intent;
    private FloatingActionButton fab;
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
        setContentView(R.layout.activity_edit_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.delete_fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(ContextContainer.getContext());
                db.removeCardInformation(Integer.parseInt(getIntent().getStringExtra("id")));
                onBackPressed();
            }
        });

        intent = getIntent();
        TextView titleText = (TextView) findViewById(R.id.textAbout);
        titleText.setText(intent.getStringExtra("title") + "\n"
                        + intent.getStringExtra("id")
                        + "\n" + intent.getStringExtra("description")
                        + "\n" + intent.getStringExtra("time")
                        + "\n" + intent.getStringExtra("data")
        );
    }




}
