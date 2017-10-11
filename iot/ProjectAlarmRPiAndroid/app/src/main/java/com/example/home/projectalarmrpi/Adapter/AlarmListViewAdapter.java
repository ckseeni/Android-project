package com.example.home.projectalarmrpi.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.home.projectalarmrpi.AlarmORM;
import com.example.home.projectalarmrpi.R;

import java.util.ArrayList;


/**
 * Created by home on 11/10/16.
 */

public class AlarmListViewAdapter extends ArrayAdapter<String>{

    private final Activity context;
    private final ArrayList<AlarmORM> alarmORMArrayList;


    public AlarmListViewAdapter(Activity context, ArrayList<AlarmORM> alarmORMArrayList) {
        super(context, R.layout.list_design_time);

        this.context = context;
        this.alarmORMArrayList = alarmORMArrayList;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_design_time,null,true);
        AlarmORM alarmORM = alarmORMArrayList.get(position);
        Log.d("mytest","get View Called");

        TextView alarmtime = (TextView)rowView.findViewById(R.id.alarmTime);
        TextView alarmName = (TextView)rowView.findViewById(R.id.alarmName);

        String time = alarmORM.get_hour()+" : "+alarmORM.get_min();

        alarmName.setText(alarmORM.get_alarm_name());
        alarmtime.setText(time);
        return rowView;
    }
}
