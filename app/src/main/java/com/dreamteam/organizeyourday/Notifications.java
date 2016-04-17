package com.dreamteam.organizeyourday;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class Notifications extends BroadcastReceiver {

    private NotificationManager nm;

    private final int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        nm=(NotificationManager ) context.getSystemService(Context.NOTIFICATION_SERVICE);


        Notification.Builder builder = new  Notification.Builder(context);
        Intent intent1 = new Intent(context, MainActivity.class);

        String title = intent.getStringExtra("title");
        String description= intent.getStringExtra("description");
        int notif_id = intent.getIntExtra("id", 0);
        int myColor=context.getResources().getColor(R.color.AccentBlue);


        PendingIntent pendingIntent = PendingIntent.getActivity(context, notif_id, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.icon)
                .setColor(myColor)
                .setTicker(title)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(title )
                .setContentText(description);
        Notification notification = builder.build();

        notification.defaults = Notification.DEFAULT_ALL ;
        nm.notify(notif_id, notification);

    }









}
