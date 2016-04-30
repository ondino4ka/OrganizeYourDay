package com.dreamteam.organizeyourday.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dreamteam.organizeyourday.AddNewCardActivity;
import com.dreamteam.organizeyourday.ContextContainer;
import com.dreamteam.organizeyourday.R;
import com.dreamteam.organizeyourday.dataOfCards.CardsData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // версия базы данных
    public static final int DATABASE_VERSION = 1;
    // имя базы данных
    public static final String DATABASE_NAME = "OranizeYourDay.bd";
    // имя таблицы
    public static final String TABLE_CONTACTS = "Orzanize";
    // названия столбцов
    public static final String ID_COLUMN = "_id";
    public static final String REMINDERS_NAME_COLUMN = "reminders_headline";
    public static final String DESCRIPTION_COLUMN = "description_reminders";
    public static final String PRIORITY_COLUMN = "priority";
    public static final String DATE = "date";
    public static final String TIME = "time";
    Context localContext;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        localContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table " + TABLE_CONTACTS + " (" +
                ID_COLUMN + " integer primary key, " +
                REMINDERS_NAME_COLUMN + " text, " +
                DESCRIPTION_COLUMN + " text, " +
                DATE + " text, " +
                TIME + " text, " +
                PRIORITY_COLUMN + " text); ";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);

    }

    public List<CardsData> getListOfDataBaseComponent() {
        List<CardsData> data = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.ID_COLUMN);
            int nameIndex = cursor.getColumnIndex(DatabaseHelper.REMINDERS_NAME_COLUMN);
            int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.DESCRIPTION_COLUMN);
            int priorityIndex = cursor.getColumnIndex(DatabaseHelper.PRIORITY_COLUMN);
            int timeIndex= cursor.getColumnIndex(DatabaseHelper.TIME);
            int dataIndex = cursor.getColumnIndex(DatabaseHelper.DATE);
            do {
                data.add(new CardsData(cursor.getInt(idIndex)
                        ,cursor.getString(nameIndex)
                        ,cursor.getString(descriptionIndex)
                        ,Integer.parseInt(cursor.getString(priorityIndex))
                        ,cursor.getString(timeIndex)
                        ,cursor.getString(dataIndex)
                        ));
            } while (cursor.moveToNext());
        }

        cursor.close();
       this.close();
        return data;
    }

    public List<CardsData> getListWithTodayDataOfCards() {
        List<CardsData> data = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String todayData = day + "." + month +"." + year;

        Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.ID_COLUMN);
            int nameIndex = cursor.getColumnIndex(DatabaseHelper.REMINDERS_NAME_COLUMN);
            int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.DESCRIPTION_COLUMN);
            int priorityIndex = cursor.getColumnIndex(DatabaseHelper.PRIORITY_COLUMN);
            int timeIndex= cursor.getColumnIndex(DatabaseHelper.TIME);
            int dataIndex = cursor.getColumnIndex(DatabaseHelper.DATE);
            do {
                if (cursor.getString(dataIndex).equals(todayData)) {
                    data.add(new CardsData(cursor.getInt(idIndex)
                            ,cursor.getString(nameIndex)
                            ,cursor.getString(descriptionIndex)
                            ,Integer.parseInt(cursor.getString(priorityIndex))
                            ,cursor.getString(timeIndex)
                            ,cursor.getString(dataIndex)
                    ));
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        this.close();
        return data;
    }

    public List<CardsData> getDataOfTodayCardsWithSamePriority(int priority){
        List<CardsData> data = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String[] selectionArgs = null;
        selectionArgs = new String[] { String.valueOf(priority)};
        Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACTS, null, "priority = ?", selectionArgs, null, null, null);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String todayData = day + "." + month +"." + year;

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.ID_COLUMN);
            int nameIndex = cursor.getColumnIndex(DatabaseHelper.REMINDERS_NAME_COLUMN);
            int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.DESCRIPTION_COLUMN);
            int priorityIndex = cursor.getColumnIndex(DatabaseHelper.PRIORITY_COLUMN);
            int timeIndex= cursor.getColumnIndex(DatabaseHelper.TIME);
            int dataIndex = cursor.getColumnIndex(DatabaseHelper.DATE);
            do {
                if (cursor.getString(dataIndex).equals(todayData)) {
                    data.add(new CardsData(cursor.getInt(idIndex)
                            , cursor.getString(nameIndex)
                            , cursor.getString(descriptionIndex)
                            , Integer.parseInt(cursor.getString(priorityIndex))
                            , cursor.getString(timeIndex)
                            , cursor.getString(dataIndex)
                    ));
                }
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");
        cursor.close();

        return data;
    }


    public int removeCardInformation(int ID){
        SQLiteDatabase database = this.getWritableDatabase();
        AddNewCardActivity.CancelNotification(ID);
        return database.delete(DatabaseHelper.TABLE_CONTACTS, ID_COLUMN + "="+ ID ,null);
    }

    public void editCard(String title, String description,String rowID)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.REMINDERS_NAME_COLUMN, title);
        contentValues.put(DatabaseHelper.DESCRIPTION_COLUMN,description);
        database.update(DatabaseHelper.TABLE_CONTACTS, contentValues, ID_COLUMN + "=" + rowID, null);
    }

    public void addCard(String title, String description,int priority,String time, String data){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.REMINDERS_NAME_COLUMN, title);
        contentValues.put(DatabaseHelper.DESCRIPTION_COLUMN,description);
        contentValues.put(DatabaseHelper.PRIORITY_COLUMN, priority);
        contentValues.put(DatabaseHelper.TIME, time);
        contentValues.put(DatabaseHelper.DATE, data);
        database.insert(DatabaseHelper.TABLE_CONTACTS, null, contentValues);
        database.close();
        }

    public List<CardsData> getCardsWithSamePriority(int priority){
        List<CardsData> data = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String[] selectionArgs = null;
        selectionArgs = new String[] { String.valueOf(priority)};
        Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACTS, null, "priority = ?", selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.ID_COLUMN);
            int nameIndex = cursor.getColumnIndex(DatabaseHelper.REMINDERS_NAME_COLUMN);
            int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.DESCRIPTION_COLUMN);
            int priorityIndex = cursor.getColumnIndex(DatabaseHelper.PRIORITY_COLUMN);
            int timeIndex= cursor.getColumnIndex(DatabaseHelper.TIME);
            int dataIndex = cursor.getColumnIndex(DatabaseHelper.DATE);
            do {
                data.add(new CardsData(cursor.getInt(idIndex)
                        ,cursor.getString(nameIndex)
                        ,cursor.getString(descriptionIndex)
                        ,Integer.parseInt(cursor.getString(priorityIndex))
                        ,cursor.getString(timeIndex)
                        ,cursor.getString(dataIndex)
                ));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");
        cursor.close();

        return data;
    }

    public int getLastId(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        cursor.moveToLast();
        return cursor.getInt(0);
    }
}


//
//       database.delete(DatabaseHelper.TABLE_CONTACTS, null, null);
 //