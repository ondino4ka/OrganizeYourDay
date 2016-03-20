package com.dreamteam.organizeyourday;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by kostya on 20.03.2016.
 */
public class Notifications extends AppCompatActivity {

    private NotificationManager nm;
    private AlarmManager am;
    private final int NOTIFICATION_ID = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);
        nm=(NotificationManager ) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);

    }
    public void showNotifications(View view)
    {
        Notification.Builder builder = new  Notification.Builder(getApplicationContext());
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.icon)
                .setTicker("Новое уведомление")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Важное уведомление")
                .setContentText("Нажмите,чтобы перейти");
        Notification notification = builder.build();
        nm.notify(NOTIFICATION_ID, notification);

        am.set(AlarmManager.RTC, System.currentTimeMillis() + 4000, pendingIntent);


    }
}
