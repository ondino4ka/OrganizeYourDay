package com.dreamteam.organizeyourday.DataBase;

/**
 * Created by kostya on 27.03.2016.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dreamteam.organizeyourday.R;


public class DataBaseTest extends AppCompatActivity implements View.OnClickListener{

    Button bdAdd, bdRead, bdClear;
    EditText etName, etDescription, etPriority;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_base_test);

        bdAdd = (Button) findViewById(R.id.bdAdd);
        bdAdd.setOnClickListener(this);

        bdRead = (Button) findViewById(R.id.bdRead);
        bdRead.setOnClickListener(this);

        bdClear = (Button) findViewById(R.id.bdClear);
        bdClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etPriority =(EditText) findViewById(R.id.etPriority);

        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String description = etDescription.getText().toString();
        String priority = etPriority.getText().toString();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.bdAdd:
                contentValues.put(DatabaseHelper.REMINDERS_NAME_COLUMN, name);
                contentValues.put(DatabaseHelper.DESCRIPTION_COLUMN, description);
                contentValues.put(DatabaseHelper.PRIORITY_COLUMN, priority);
                database.insert(DatabaseHelper.TABLE_CONTACTS, null, contentValues);
                break;

            case R.id.bdRead:
                Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACTS, null, null, null, null, null, null, null);

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

            case R.id.bdClear:
                database.delete(DatabaseHelper.TABLE_CONTACTS, null, null);
                break;
        }
       // databaseHelper.close();
    }
}