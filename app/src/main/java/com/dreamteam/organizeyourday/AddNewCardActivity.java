package com.dreamteam.organizeyourday;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;
import com.dreamteam.organizeyourday.Notification.Notifications;


public class AddNewCardActivity extends AppCompatActivity {

private boolean isEnterAnimationComplete = false;
    public static AlarmManager am;

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
        ThemeManager.setCurrentTheme(this);
        super.onCreate(savedInstanceState);

        am = (AlarmManager) getSystemService(ALARM_SERVICE);

        setContentView(R.layout.activity_add_new_card);
        TimePicker timePicker = (TimePicker)findViewById(R.id.time);
        timePicker.setIs24HourView(true);

        final TextInputEditText titleText = (TextInputEditText) findViewById(R.id.inputTitleText);
        final TextInputEditText descriptionText = (TextInputEditText) findViewById(R.id.inputTextDescription);
        final Spinner prioritySpinner = (Spinner)findViewById(R.id.prioritySpinner);
        Button addButton = (Button) findViewById(R.id.addButton);

        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleText.getText().toString().equals("")) {
                    notifyMe();
                    return;
                }
                DatabaseHelper db = new DatabaseHelper(ContextContainer.getContext());
                db.addCard(
                        titleText.getText().toString()
                        , descriptionText.getText().toString()
                        , prioritySpinner.getSelectedItemPosition()
                );

                Intent intent = new Intent(getApplicationContext(), Notifications.class);
                intent.putExtra("title", titleText.getText().toString());
                intent.putExtra("description", descriptionText.getText().toString());
                intent.putExtra("id", db.getLastId());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                db.getLastId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                am.set(AlarmManager.RTC, System.currentTimeMillis() + 10000, pendingIntent);

                onBackPressed();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    void notifyMe() {
        Toast toast = Toast.makeText(this, "Write title name!", Toast.LENGTH_SHORT);
        toast.show();
    }

  public static void CancelNotification(int id)
    {
        Intent intent = new Intent(ContextContainer.getContext(), Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ContextContainer.getContext(),
            id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.cancel(pendingIntent);
    }
}
