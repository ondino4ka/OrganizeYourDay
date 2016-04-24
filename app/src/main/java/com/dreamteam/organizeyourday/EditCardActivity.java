package com.dreamteam.organizeyourday;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;
import com.dreamteam.organizeyourday.Notification.Notifications;

public class EditCardActivity extends AppCompatActivity {

    Intent intent;
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

        intent = getIntent();
        TextView titleText = (TextView) findViewById(R.id.textAbout);
        titleText.setText(intent.getStringExtra("title"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editcard_toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.removeCardButton){
            DatabaseHelper db = new DatabaseHelper(ContextContainer.getContext());
            db.removeCardInformation(Integer.parseInt(getIntent().getStringExtra("id")));
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
