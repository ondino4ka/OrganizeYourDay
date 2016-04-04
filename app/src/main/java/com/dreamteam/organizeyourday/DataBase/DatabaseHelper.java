package com.dreamteam.organizeyourday.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
}