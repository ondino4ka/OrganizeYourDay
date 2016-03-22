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
    public void onReceive(Context context, Intent actionIntent) {
        nm=(NotificationManager ) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new  Notification.Builder(context);
        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.icon)
                .setTicker("Новое уведомление")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Важное уведомление")
                .setContentText("Нажмите чтобы перейти");
        Notification notification = builder.build();

        notification.defaults = Notification.DEFAULT_ALL ;
        nm.notify(NOTIFICATION_ID, notification);

    }







}
