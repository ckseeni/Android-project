package com.example.home.projectalarmrpi;

import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.example.home.projectalarmrpi.Adapter.FragmentChanger;

import java.net.Socket;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements FragmentChanger {

    private static String ipAdd;
    private static int ipPort;
    private AlarmDAO alarmDAO;
    private ArrayList<AlarmORM> alarmORMArrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        alarmDAO=new AlarmDAO(this);
        alarmORMArrayList = alarmDAO.alarmORMArrayList();

        if(savedInstanceState == null){
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.mainFrame,EstablishConnection.newInstance(),"check connection fragment").
                    addToBackStack(null).
                    commit();
        }
    }

    public ArrayList<AlarmORM> getAlarmORMArrayList(){

        return alarmDAO.alarmORMArrayList();
    }

    @Override
    public Cursor getCursor() {
        return alarmDAO.alarmORMCursor();
    }


    @Override
    public void changeToAlarmFrag(String ip, int port) {
        ipAdd = ip;
        ipPort = port;

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.mainFrame,Alarm.newInstance(ip,port),"changing to alarm page").
                addToBackStack(null).
                commit();

    }

    @Override
    public void changeToSingleAlarmFrag(AlarmORM alarmORM) {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.mainFrame,SingleAlarmFrag.newInstance(alarmORM),"changing to single alarm").
                addToBackStack(null).
                commit();
    }

    @Override
    public void changeToCreateNewAlarmPage() {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.mainFrame,SetNewAlarm.newInstance(),"check connection fragment").
                addToBackStack(null).
                commit();
    }

    @Override
    public void addNewAlarm(AlarmORM alarmORM) {
        alarmDAO.addAlarm(alarmORM);
        Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
        alarmORMArrayList = alarmDAO.alarmORMArrayList();
        FragmentManager fm =  getSupportFragmentManager();
        fm.popBackStack();


    }

    @Override
    public void editListItem(AlarmORM alarmORM) {
        alarmDAO.updateAlarm(alarmORM);
        FragmentManager fm =  getSupportFragmentManager();
        fm.popBackStack();

    }

    @Override
    public void deleteListItem(AlarmORM alarmORM) {
        alarmDAO.deleteAlarm(alarmORM.get_id());
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }


}
