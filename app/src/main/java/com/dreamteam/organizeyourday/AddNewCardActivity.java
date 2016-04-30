package com.dreamteam.organizeyourday;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;
import com.dreamteam.organizeyourday.Notification.Notifications;

import java.util.Calendar;


public class AddNewCardActivity extends AppCompatActivity {

private boolean isEnterAnimationComplete = false;
    public static AlarmManager am;
    TextInputEditText titleText;
    TextInputEditText descriptionText;
    Spinner prioritySpinner;
    TimePicker time;
    TextView data;

    public void onEnterAnimationComplete()
    {
        isEnterAnimationComplete = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.setCurrentTheme(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_new_card);

        am = (AlarmManager) getSystemService(ALARM_SERVICE);

        time = (TimePicker)findViewById(R.id.time);
        data = (TextView)findViewById(R.id.data);
        final Calendar calendar = Calendar.getInstance();

        data.setText(calendar.get(Calendar.DAY_OF_MONTH)
                                    +"." + calendar.get(Calendar.MONTH)
                                    +"." + calendar.get(Calendar.YEAR)
        );

        titleText = (TextInputEditText) findViewById(R.id.inputTitleText);
        descriptionText = (TextInputEditText) findViewById(R.id.inputTextDescription);
        prioritySpinner = (Spinner)findViewById(R.id.prioritySpinner);
        Button timeButton = (Button)findViewById(R.id.timeButton);

        TimePicker timePicker = (TimePicker)findViewById(R.id.time);
        timePicker.setIs24HourView(true);

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dateDialog = new com.dreamteam.organizeyourday.DatePicker();
                dateDialog.show(getSupportFragmentManager(), "datePicker");
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
        MainActivity.am.cancel(pendingIntent);
    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_card_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.addCardButton) {
            if (titleText.getText().toString().equals("")) {
                notifyMe();
                return false;
            }
            DatabaseHelper db = new DatabaseHelper(ContextContainer.getContext());

            db.addCard(
                    titleText.getText().toString()
                    , descriptionText.getText().toString()
                    , prioritySpinner.getSelectedItemPosition()
                    , time.getCurrentHour() + ":" + time.getCurrentMinute()
                    , data.getText().toString()
            );
            Intent intent = new Intent(getApplicationContext(), Notifications.class);
            intent.putExtra("title", titleText.getText().toString());
            intent.putExtra("description", descriptionText.getText().toString());
            intent.putExtra("id", db.getLastId());
            intent.putExtra("priority", prioritySpinner.getSelectedItemPosition());

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    db.getLastId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

            String[] array = data.getText().toString().split("\\.");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(array[2]));
            calendar.set(Calendar.MONTH ,Integer.parseInt(array[1]) );
            calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(array[0]));
            calendar.set(Calendar.HOUR_OF_DAY, time.getCurrentHour());
            calendar.set(Calendar.MINUTE, time.getCurrentMinute());
            calendar.set(Calendar.SECOND, 0);

            am.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
