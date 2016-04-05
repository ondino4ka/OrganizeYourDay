package com.dreamteam.organizeyourday.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dreamteam.organizeyourday.ContextContainer;
import com.dreamteam.organizeyourday.R;
import com.dreamteam.organizeyourday.dataOfCards.CardsData;

import java.util.ArrayList;
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
                PRIORITY_COLUMN + " text); ";

        db.execSQL(query);
    }

    //  public void onCreate(SQLiteDatabase db) {
    //      db.execSQL("create table " + TABLE_CONTACTS + "(" + ID_COLUMN
    //             + " integer primary key," + REMINDERS_NAME_COLUMN + " text," + DESCRIPTION_COLUMN + " text" + ")");
//
    //  }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);

    }

    public List<CardsData> getListOfDataBaseComponent() {
        List<CardsData> data = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        //ContentValues contentValues = new ContentValues();
        Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.ID_COLUMN);
            int nameIndex = cursor.getColumnIndex(DatabaseHelper.REMINDERS_NAME_COLUMN);
            int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.DESCRIPTION_COLUMN);
            int priorityIndex = cursor.getColumnIndex(DatabaseHelper.PRIORITY_COLUMN);
            do {
                data.add(new CardsData(cursor.getInt(idIndex),cursor.getString(nameIndex)));
            } while (cursor.moveToNext());
        }

        cursor.close();
       this.close();
        return data;
    }


    public int removeCardInformation(int ID){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(DatabaseHelper.TABLE_CONTACTS, ID_COLUMN + "="+ ID ,null);
    }

    public void addCard(String title){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.REMINDERS_NAME_COLUMN, title);
        database.insert(DatabaseHelper.TABLE_CONTACTS, null, contentValues);
        database.close();

        }
}