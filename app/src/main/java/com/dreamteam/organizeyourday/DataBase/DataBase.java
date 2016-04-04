package com.dreamteam.organizeyourday.DataBase;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dreamteam.organizeyourday.ContextContainer;
import com.dreamteam.organizeyourday.dataOfCards.CardsData;

import java.util.ArrayList;
import java.util.List;


public class DataBase {

    Context context;
    DatabaseHelper databaseHelper;
    public DataBase() {
        context = ContextContainer.getContainer();
        databaseHelper = new DatabaseHelper(context);
    }

    public List<CardsData> getListOfDataBaseComponent() {
        List<CardsData> data = new ArrayList<>();

        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
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
                data.add(new CardsData(cursor.getString(nameIndex)));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog", "0 rows");

        cursor.close();
        databaseHelper.close();
        return data;
    }


    public int removeCardInformation(String name){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        return database.delete(DatabaseHelper.TABLE_CONTACTS, DatabaseHelper.REMINDERS_NAME_COLUMN + "="+ name ,null);
    }
}
