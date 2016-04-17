package com.dreamteam.organizeyourday.DataBase;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dreamteam.organizeyourday.ContextContainer;
import com.dreamteam.organizeyourday.Notifications;
import com.dreamteam.organizeyourday.R;
import com.dreamteam.organizeyourday.ThemeManager;


public class DataBaseTest extends AppCompatActivity implements View.OnClickListener{

    Button bdAdd, bdRead, bdClear, bdSort, bdSearch;
    EditText etName, etDescription, etPriority, etSearch;
    DatabaseHelper databaseHelper;

    private AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.setCurrentTheme(this);
        super.onCreate(savedInstanceState);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        setContentView(R.layout.data_base_test);

        bdAdd = (Button) findViewById(R.id.bdAdd);
        bdAdd.setOnClickListener(this);

        bdRead = (Button) findViewById(R.id.bdRead);
        bdRead.setOnClickListener(this);

        bdClear = (Button) findViewById(R.id.bdClear);
        bdClear.setOnClickListener(this);

        bdSort = (Button) findViewById(R.id.bdSort);
        bdSort.setOnClickListener(this);

        bdSearch = (Button) findViewById(R.id.bdSearch);
        bdSearch.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etPriority =(EditText) findViewById(R.id.etPriority);
        etSearch = (EditText) findViewById(R.id.etSearch);

        databaseHelper = new DatabaseHelper(this);

    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String description = etDescription.getText().toString();
        String priority = etPriority.getText().toString();
        String search = etSearch.getText().toString();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        String[] selectionArgs = null;

        switch (v.getId()) {

            case R.id.bdAdd:
                contentValues.put(DatabaseHelper.REMINDERS_NAME_COLUMN, name);
                contentValues.put(DatabaseHelper.DESCRIPTION_COLUMN, description);
                contentValues.put(DatabaseHelper.PRIORITY_COLUMN, priority);
                database.insert(DatabaseHelper.TABLE_CONTACTS, null, contentValues);

                Intent intent = new Intent(ContextContainer.getContext(), Notifications.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(ContextContainer.getContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                am.set(AlarmManager.RTC, System.currentTimeMillis() + 10000, pendingIntent);


                break;

            //Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACTS, null, null, null, null, null, "reminders_headline, priority");
            case R.id.bdRead:

                Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DatabaseHelper.ID_COLUMN);
                    int nameIndex = cursor.getColumnIndex(DatabaseHelper.REMINDERS_NAME_COLUMN);
                    int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.DESCRIPTION_COLUMN);
                    int priorityIndex = cursor.getColumnIndex(DatabaseHelper.PRIORITY_COLUMN);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", description = " + cursor.getString(descriptionIndex) +
                                ", priority = " + cursor.getString(priorityIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");
                cursor.close();
                break;

            case R.id.bdSearch:
                selectionArgs = new String[] { search };
                Cursor cursor1 = database.query(DatabaseHelper.TABLE_CONTACTS, null, "reminders_headline = ?", selectionArgs, null, null, null);

                if (cursor1.moveToFirst()) {
                    int idIndex = cursor1.getColumnIndex(DatabaseHelper.ID_COLUMN);
                    int nameIndex = cursor1.getColumnIndex(DatabaseHelper.REMINDERS_NAME_COLUMN);
                    int descriptionIndex = cursor1.getColumnIndex(DatabaseHelper.DESCRIPTION_COLUMN);
                    int priorityIndex = cursor1.getColumnIndex(DatabaseHelper.PRIORITY_COLUMN);
                    do {
                        Log.d("mLog", "ID = " + cursor1.getInt(idIndex) +
                                ", name = " + cursor1.getString(nameIndex) +
                                ", description = " + cursor1.getString(descriptionIndex) +
                                ", priority = " + cursor1.getString(priorityIndex));

                    } while (cursor1.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor1.close();
                break;



            case R.id.bdSort:

                Cursor cursor2 = database.query(DatabaseHelper.TABLE_CONTACTS, null, null, null, null, null, "reminders_headline, priority");

                if (cursor2.moveToFirst()) {
                    int idIndex = cursor2.getColumnIndex(DatabaseHelper.ID_COLUMN);
                    int nameIndex = cursor2.getColumnIndex(DatabaseHelper.REMINDERS_NAME_COLUMN);
                    int descriptionIndex = cursor2.getColumnIndex(DatabaseHelper.DESCRIPTION_COLUMN);
                    int priorityIndex = cursor2.getColumnIndex(DatabaseHelper.PRIORITY_COLUMN);
                    do {
                        Log.d("mLog", "ID = " + cursor2.getInt(idIndex) +
                                ", name = " + cursor2.getString(nameIndex) +
                                ", description = " + cursor2.getString(descriptionIndex) +
                                ", priority = " + cursor2.getString(priorityIndex));

                    } while (cursor2.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor2.close();
                break;

            case R.id.bdClear:
                database.delete(DatabaseHelper.TABLE_CONTACTS, null, null);
                break;
        }
       // databaseHelper.close();
    }
}