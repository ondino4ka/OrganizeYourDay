package com.dreamteam.organizeyourday;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;

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
        final Intent intent = getIntent();

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
