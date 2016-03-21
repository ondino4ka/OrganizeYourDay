package com.dreamteam.organizeyourday;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by kostya on 21.03.2016.
 */
public class TimeNotification extends AppCompatActivity {


    private AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
    }


    public void showNotifications(View view) {

        Intent intent = new Intent(getApplicationContext(), Notifications.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.set(AlarmManager.RTC, System.currentTimeMillis() + 4000, pendingIntent);
    }
}