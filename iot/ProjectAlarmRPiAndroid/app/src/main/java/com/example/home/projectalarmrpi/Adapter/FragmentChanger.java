package com.example.home.projectalarmrpi.Adapter;

import android.database.Cursor;

import com.example.home.projectalarmrpi.AlarmORM;

import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by home on 10/10/16.
 */

public interface FragmentChanger {


    //change to alarm setting page
    public void changeToAlarmFrag(String ip, int port);

    //changet to single alarm view
    public void changeToSingleAlarmFrag(AlarmORM alarmORM);


    public void changeToCreateNewAlarmPage();

    public void addNewAlarm(AlarmORM alarmORM);

    public ArrayList<AlarmORM> getAlarmORMArrayList();

    public Cursor getCursor();

    //edit the list item
    public void editListItem(AlarmORM alarmORM);

    //delete a list item
    public void deleteListItem(AlarmORM alarmORM);




}
