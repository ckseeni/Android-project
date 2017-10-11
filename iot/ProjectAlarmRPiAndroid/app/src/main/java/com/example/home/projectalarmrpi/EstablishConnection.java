package com.example.home.projectalarmrpi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.home.projectalarmrpi.Adapter.FragmentChanger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class EstablishConnection extends Fragment {

    View view;
    private EditText ip;
    private EditText port;
    private Socket clientSocket;
    private Button checkConn;
    private int response;
    private String ip_address;
    private int ip_port;
    FragmentChanger fragmentChanger;



    public static EstablishConnection newInstance(){
        return new EstablishConnection();
    }

    public EstablishConnection(){

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
        view = inflater.inflate(R.layout.frame_establish_conn,container,false);
        ip = (EditText)view.findViewById(R.id.ip);
        port = (EditText)view.findViewById(R.id.port);
        checkConn = (Button)view.findViewById(R.id.button);

        checkConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
            }
        });
        return view;
    }


    public void checkConnection(){

        final Activity activity = getActivity();

        ip_address = ip.getText().toString();
        ip_port = Integer.parseInt(port.getText().toString());
        clientSocket = null;
        Toast.makeText(activity ,"connecting", Toast.LENGTH_SHORT).show();
        Log.d("mytest","test");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket(ip_address,ip_port);
                    OutputStream out = clientSocket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.writeBytes("handshake_connection");
                    dos.close();
                    out.close();
                    clientSocket.close();
                    fragmentChanger.changeToAlarmFrag(ip_address,ip_port);
                    Log.d("mytest","success");

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();






    }


}




