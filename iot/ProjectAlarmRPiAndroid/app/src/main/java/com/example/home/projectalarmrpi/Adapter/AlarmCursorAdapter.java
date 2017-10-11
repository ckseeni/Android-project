package com.example.home.projectalarmrpi.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.example.home.projectalarmrpi.R;

/**
 * Created by home on 12/10/16.
 */

public class AlarmCursorAdapter extends ResourceCursorAdapter{

    TextView a_name;
    TextView a_time;



    public AlarmCursorAdapter(Context context, int layout, Cursor c, boolean autoRequery) {
        super(context, layout, c, autoRequery);
        Log.d("mytest","cons of cursor adapter");
    }


    @Override
    public View newView(Context context, Cursor cur, ViewGroup parent) {
        Log.d("mytest","newview of cursor adapter");
        LayoutInflater li = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return li.inflate(R.layout.list_design_time, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Log.d("mytest","bindview of cursor adapter");
        a_name = (TextView)view.findViewById(R.id.alarmName);
        a_time = (TextView)view.findViewById(R.id.alarmTime);

        a_name.setText(cursor.getString(1));
        String temp = cursor.getInt(2)+" : "+cursor.getInt(3);
        a_time.setText(temp);

    }



}