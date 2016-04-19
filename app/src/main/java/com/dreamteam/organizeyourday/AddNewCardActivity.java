package com.dreamteam.organizeyourday;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;
import com.dreamteam.organizeyourday.Notification.Notifications;


public class AddNewCardActivity extends AppCompatActivity {

private boolean isEnterAnimationComplete = false;
    private AlarmManager am;

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
                        ,descriptionText.getText().toString()
                        ,prioritySpinner.getSelectedItemPosition()
                );

                Intent intent = new Intent(getApplicationContext(), Notifications.class);
                intent.putExtra("title", titleText.getText().toString());
                intent.putExtra("description", descriptionText.getText().toString());
                intent.putExtra("id", prioritySpinner.getSelectedItemPosition());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                prioritySpinner.getSelectedItemPosition(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
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
}
