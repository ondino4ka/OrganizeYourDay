package com.dreamteam.organizeyourday;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dreamteam.organizeyourday.DataBase.DataBaseTest;
import com.dreamteam.organizeyourday.Notification.Notifications;


public class TimeNotification extends AppCompatActivity {

    //МОЖНО НАХРЕН УДАЛИТЬ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private AlarmManager am;
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
        ThemeManager.setCurrentTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
    }


    public void showNotifications(View view) {

        Intent intent = new Intent(getApplicationContext(), Notifications.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(ContextContainer.getContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.set(AlarmManager.RTC, System.currentTimeMillis() + 10000, pendingIntent);
    }

//для базы данных
    public void openlayout(View view) {
        Intent intent;
        intent = new Intent(TimeNotification.this, DataBaseTest.class);
        startActivity(intent);

    }
}