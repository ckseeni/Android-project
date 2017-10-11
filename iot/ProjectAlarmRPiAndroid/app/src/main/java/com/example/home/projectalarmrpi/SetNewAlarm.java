package com.example.home.projectalarmrpi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.home.projectalarmrpi.Adapter.FragmentChanger;

import java.util.ArrayList;

/**
 * Created by home on 11/10/16.
 */

public class SetNewAlarm extends Fragment {

    Button create_alarm;
    AlarmDAO alarmDAO;
    EditText anmae;
    TimePicker timePicker;
    FragmentChanger fragmentChanger;


    public static SetNewAlarm newInstance(){
        return new SetNewAlarm();
    }

    public SetNewAlarm(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentChanger){
            fragmentChanger = (FragmentChanger)context;
        }
        else{
            throw new ClassCastException(context.toString()+" must implement listener");

        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_create_alarm,container,false);
        alarmDAO=new AlarmDAO(getActivity());

        anmae = (EditText) view.findViewById(R.id.new_alarm_name);
        timePicker = (TimePicker)view.findViewById(R.id.new_time_picker);
        create_alarm = (Button)view.findViewById(R.id.set_new_alarm);


        create_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFun();
            }
        });




        return view;
    }


    public void setFun(){
        AlarmORM alarmORM = new AlarmORM();

        alarmORM.set_alarm_name(anmae.getText().toString());
        int min, hour;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            min = timePicker.getMinute();
            hour = timePicker.getHour();
        }else{
            min = timePicker.getCurrentMinute();
            hour = timePicker.getCurrentHour();
        }
        alarmORM.set_hour(hour);
        alarmORM.set_min(min);
        fragmentChanger.addNewAlarm(alarmORM);
    }







}
