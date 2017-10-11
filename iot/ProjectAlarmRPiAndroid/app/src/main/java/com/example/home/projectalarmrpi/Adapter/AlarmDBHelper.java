package com.example.home.projectalarmrpi.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by home on 11/10/16.
 */

public class AlarmDBHelper extends SQLiteOpenHelper {

    public static final String DBName = "alarm.db";
    public static final String TableName = "alarmtable";
    public static final String column_id = "_id";
    public static final String column_alarm_name = "alarmname";
    public static final String column_alarm_hour = "alarmhour";
    public static final String column_alarm_min = "alarmmin";



    public AlarmDBHelper(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE "+TableName+" ("+column_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+column_alarm_name
                +" TEXT, "+column_alarm_hour+" INTEGER, "+column_alarm_min+" INTEGER);";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+DBName);
        onCreate(db);
    }
}
