package com.example.home.projectalarmrpi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.home.projectalarmrpi.Adapter.AlarmDBHelper;

import java.util.ArrayList;

/**
 * Created by home on 11/10/16.
 */

public class AlarmDAO {
    public SQLiteDatabase database;
    public AlarmDBHelper dbHelper;
    public String[] allCols={
            AlarmDBHelper.column_id,
            AlarmDBHelper.column_alarm_name,
            AlarmDBHelper.column_alarm_hour,
            AlarmDBHelper.column_alarm_min
    };


    public AlarmDAO(Context context){
        dbHelper = new AlarmDBHelper(context);
    }

    public void addAlarm(AlarmORM alarmORM){
        ContentValues contentValues = new ContentValues();
        //contentValues.put(AlarmDBHelper.column_id,alarmORM.get_id());
        contentValues.put(AlarmDBHelper.column_alarm_name,alarmORM.get_alarm_name());
        contentValues.put(AlarmDBHelper.column_alarm_hour,alarmORM.get_hour());
        contentValues.put(AlarmDBHelper.column_alarm_min,alarmORM.get_min());

        database = dbHelper.getWritableDatabase();
        database.insert(AlarmDBHelper.TableName,null,contentValues);
        database.close();

    }

    public ArrayList<AlarmORM> alarmORMArrayList(){
        ArrayList<AlarmORM> alarms = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(AlarmDBHelper.TableName,allCols,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            AlarmORM temp = cursorToAlarmORM(cursor);
            alarms.add(temp);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return alarms;
    }


    public AlarmORM cursorToAlarmORM(Cursor cursor){
        AlarmORM alarmORM = new AlarmORM();
        alarmORM.set_id(cursor.getLong(0));
        alarmORM.set_alarm_name(cursor.getString(1));
        alarmORM.set_hour(cursor.getInt(2));
        alarmORM.set_min(cursor.getInt(3));

        return alarmORM;

    }

    public Cursor alarmORMCursor(){
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(AlarmDBHelper.TableName,allCols,null,null,null,null,null);
        cursor.moveToFirst();
        //cursor.close();
        database.close();
        return cursor;
    }


    public void deleteAlarm(long id){

        database = dbHelper.getWritableDatabase();
        database.delete(AlarmDBHelper.TableName, AlarmDBHelper.column_id+" = "+id,null);

    }

    public void updateAlarm(AlarmORM alarmORM){
        database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AlarmDBHelper.column_alarm_name,alarmORM.get_alarm_name());
        contentValues.put(AlarmDBHelper.column_alarm_hour,alarmORM.get_hour());
        contentValues.put(AlarmDBHelper.column_alarm_min,alarmORM.get_min());

        database.update(AlarmDBHelper.TableName,contentValues,AlarmDBHelper.column_id+"="+alarmORM.get_id(),null);

    }




}
