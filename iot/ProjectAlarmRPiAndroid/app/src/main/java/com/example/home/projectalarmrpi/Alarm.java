package com.example.home.projectalarmrpi;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.home.projectalarmrpi.Adapter.AlarmCursorAdapter;
import com.example.home.projectalarmrpi.Adapter.FragmentChanger;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by home on 10/10/16.
 */

public class Alarm extends Fragment {

    Context mcontext;
    FragmentChanger fragmentChanger;
    View view;

    private Socket clientSocket;
    private Button onButton;
    private Button offButton;
    private Button newAlarmButton;
    private Button uploadToRPIButton;

    private static String ip_add;
    private static int ip_port;

    ListView listView;
    AlarmCursorAdapter alarmCursorAdapter;




    public static Alarm newInstance(String ip, int port){
        ip_add = ip;
        ip_port = port;
        return new Alarm();
    }

    public Alarm(){

    }


    @Override
    public void onAttach(Context context) {
        mcontext=context;
        super.onAttach(context);
        if(context instanceof FragmentChanger){
            fragmentChanger = (FragmentChanger)context;
        }
        else{
            throw new ClassCastException(context.toString()+" must implement listener");

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        alarmCursorAdapter.notifyDataSetChanged();
        uploadTimeToRPI();
       // alarmListViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroy() {
        Toast.makeText(getActivity(),"uploading data to RPi",Toast.LENGTH_LONG).show();
        uploadTimeToRPI();
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("mytest","oncreate entered");
        view = inflater.inflate(R.layout.frame_alarm,container,false);

        onButton =(Button)view.findViewById(R.id.onButton);
        offButton = (Button)view.findViewById(R.id.offButton);
        newAlarmButton = (Button)view.findViewById(R.id.jmp_new_alarm_page);
        uploadToRPIButton = (Button)view.findViewById(R.id.upload_to_pi);
        Log.d("mytest","found all xml");

        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOn();
            }
        });
        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOff();
            }
        });
        newAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChanger.changeToCreateNewAlarmPage();
            }
        });
        uploadToRPIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadTimeToRPI();
            }
        });
        Log.d("mytest","calling list");
        setListView();

        return view;
    }


    public void setListView(){
        final ArrayList<AlarmORM> alarmORMArrayList = fragmentChanger.getAlarmORMArrayList();
        Log.d("mytest","returned to alarm after fetch");

        alarmCursorAdapter = new AlarmCursorAdapter(getActivity(),R.layout.list_design_time,fragmentChanger.getCursor(),true);
        listView=(ListView) view.findViewById(R.id.alarmList);
        listView.setAdapter(alarmCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("examine","success");
                Toast.makeText(getActivity(),"clicked "+alarmORMArrayList.get(position).get_alarm_name(),Toast.LENGTH_LONG).show();
                fragmentChanger.changeToSingleAlarmFrag(alarmORMArrayList.get(position));
            }
        });

        Log.d("mytest","list view integrated");
    }


    public void turnOn(){
        Toast.makeText(getActivity(),ip_add+":"+ip_port,Toast.LENGTH_LONG).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket(ip_add,ip_port);
                    OutputStream out = clientSocket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.writeBytes("on");
                    dos.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();
    }

    public void turnOff(){
        Toast.makeText(getActivity(),ip_add+":"+ip_port,Toast.LENGTH_LONG).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket(ip_add,ip_port);
                    OutputStream out = clientSocket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.writeBytes("off");
                    dos.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();
    }


    public void uploadTimeToRPI(){
        Toast.makeText(getActivity(),"uploading data to RPi",Toast.LENGTH_LONG).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String time="";
                    int min,hour;
                    clientSocket = new Socket(ip_add, ip_port);
                    OutputStream out = clientSocket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    ArrayList<AlarmORM> alarmORMArrayList = fragmentChanger.getAlarmORMArrayList();
                    Log.d("mytest1", String.valueOf(alarmORMArrayList.size()));
                    for(AlarmORM x : alarmORMArrayList){
                        min=x.get_min();
                        hour=x.get_hour();
                        /*conversion not needed since changed NTPchrony
                        if(min-30 < 0){
                            min = min-30+60;
                        }else{
                            min = min - 30;
                        }

                        if(hour-5 < 0){
                            hour = hour-4+24;
                        }
                        else{
                            hour = hour - 4;
                        }*/

                        if(time.equals(""))
                            time = min+"-"+hour;
                        else
                            time = time+" "+min+"-"+hour;
                    }
                    if(time.equals("")){
                        time="# ";
                    }
                    dos.writeBytes(time);
                    dos.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();


    }


}
