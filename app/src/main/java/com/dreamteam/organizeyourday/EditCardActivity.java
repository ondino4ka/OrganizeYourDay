package com.dreamteam.organizeyourday;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;

public class EditCardActivity extends AppCompatActivity {

    Intent intent;
    private FloatingActionButton fab;
    private boolean isEnterAnimationComplete = false;
    TextInputEditText titleText;
    TextInputEditText descriptionText;
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

        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_toolbar);
        setSupportActionBar(toolbar);
        intent = getIntent();
        appBarLayout.setTitle(intent.getStringExtra("title"));
        titleText = (TextInputEditText) findViewById(R.id.editTitleText);
        descriptionText = (TextInputEditText) findViewById(R.id.editDescriptionText);
        titleText.setText(intent.getStringExtra("title"));
        descriptionText.setText(intent.getStringExtra("description"));

        fab = (FloatingActionButton) findViewById(R.id.delete_fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(ContextContainer.getContext());
                db.removeCardInformation(Integer.parseInt(getIntent().getStringExtra("id")));
                onBackPressed();
            }
        });

        AppCompatButton saveButton = (AppCompatButton)findViewById(R.id.saveEditCard);
        assert saveButton != null;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(ContextContainer.getContext());
                db.editCard(titleText.getText().toString(),descriptionText.getText().toString(),intent.getStringExtra("id"));
                onBackPressed();
            }
        });
    }




}
