package com.example.home.projectalarmrpi;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.home.projectalarmrpi.Adapter.FragmentChanger;

import java.util.ArrayList;

/**
 * Created by home on 11/10/16.
 */

public class SingleAlarmFrag extends Fragment {

    private static AlarmORM alarmORM;
    Button delete;
    Button edit;
    FragmentChanger fragmentChanger;

    public static SingleAlarmFrag newInstance(AlarmORM temp){

        alarmORM = temp;
        return new SingleAlarmFrag();
    }


    public SingleAlarmFrag(){

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
        final Bundle args = getArguments();

        View view = inflater.inflate(R.layout.single_alarm_view,container,false);
        final EditText xmlAlarmName = (EditText) view.findViewById(R.id.single_view_alarm_name);
        final TimePicker timePicker = (TimePicker)view.findViewById(R.id.timePicker);
        edit = (Button) view.findViewById(R.id.single_view_edit_button);
        delete = (Button)view.findViewById(R.id.single_view_delete_button);




        xmlAlarmName.setText(alarmORM.get_alarm_name());
        timePicker.setIs24HourView(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setMinute(alarmORM.get_hour());
            timePicker.setHour(alarmORM.get_min());
        }else {
            timePicker.setCurrentHour(alarmORM.get_hour());
            timePicker.setCurrentMinute(alarmORM.get_min());
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int min, hour;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    min = timePicker.getMinute();
                    hour = timePicker.getHour();
                }else{
                    min = timePicker.getCurrentMinute();
                    hour = timePicker.getCurrentHour();
                }


                String temp = hour+"-"+min;
                alarmORM.set_hour(hour);
                alarmORM.set_min(min);
                alarmORM.set_alarm_name(xmlAlarmName.getText().toString());
                fragmentChanger.editListItem(alarmORM);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChanger.deleteListItem(alarmORM);

            }
        });




        return view;
    }
}
