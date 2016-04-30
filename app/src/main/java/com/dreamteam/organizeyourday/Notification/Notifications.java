package com.dreamteam.organizeyourday.Notification;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.dreamteam.organizeyourday.MainActivity;
import com.dreamteam.organizeyourday.R;


public class Notifications extends BroadcastReceiver {

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        nm=(NotificationManager ) context.getSystemService(Context.NOTIFICATION_SERVICE);


        Notification.Builder builder = new  Notification.Builder(context);
        Intent intent1 = new Intent(context, MainActivity.class);

        String title = intent.getStringExtra("title");
        String description= intent.getStringExtra("description");
        int notif_id = intent.getIntExtra("id", 0);
        int priority = intent.getIntExtra("priority", 0);
        int myColor=0;

        switch (priority){
            case 0: {
                myColor=context.getResources().getColor(R.color.GreenNotification);
                break;
            }
            case 1: {
                myColor = context.getResources().getColor(R.color.YellowNotification);
                break;
            }
            case 2:{
                myColor = context.getResources().getColor(R.color.AccentPink);
                break;
            }

            case 3:{
                myColor = context.getResources().getColor(R.color.RedNotification);
                break;
            }
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(context, notif_id, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_error_24dp)
                .setColor(myColor)
                .setTicker(title)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(title )
                .setContentText(description);
        Notification notification = builder.build();

        notification.defaults = Notification.DEFAULT_ALL ;

        switch (priority){
            case 0: {

                break;
            }
            case 1: {

                break;
            }
            case 2:{
                notification.flags=notification.flags | Notification.FLAG_ONGOING_EVENT;
                break;
            }

            case 3:{
               notification.flags=notification.flags | Notification.FLAG_INSISTENT | Notification.FLAG_ONGOING_EVENT;
                break;
            }
        }
        nm.notify(notif_id, notification);

    }









}
