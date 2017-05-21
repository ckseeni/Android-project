package com.example.priority;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.*;
import android.widget.*;
import java.util.*;


public class MainActivity extends ActionBarActivity {
    private HashMap<Integer,ArrayList<String>> h = new HashMap<Integer,ArrayList<String>>();
    private PriorityQueue<Integer> pri = new PriorityQueue<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onInsert(View view){
        TextView Job = (TextView)findViewById(R.id.Job);
        TextView priority = (TextView)findViewById(R.id.pri);
        if(Job.getText().toString().equals("")||priority.getText().toString().equals(""))
            Toast.makeText(MainActivity.this,"Fill all fields!!",Toast.LENGTH_SHORT).show();
        else {
            int p = Integer.parseInt(priority.getText().toString());
            if(h.containsKey(-p)){
                ArrayList<String> alr = h.get(-p);
                alr.add(Job.getText().toString());
                h.put(-p,alr);
            }
            else{
                pri.add(-p);
                ArrayList<String> a = new ArrayList<String>();
                a.add(Job.getText().toString());
                h.put(-p,a);
            }
            Toast.makeText(MainActivity.this, "The Job "+Job.getText().toString() + " with priority " + p + " is inserted!", Toast.LENGTH_LONG).show();
            Job.setText("");
            priority.setText("");
        }
    }
    public void onRetrieve(View view){
        if(pri.isEmpty())
            Toast.makeText(MainActivity.this,"All jobs are deployed!!Please Insert Job to proceed!!", Toast.LENGTH_SHORT).show();
        else{
            int t = pri.poll();
            ArrayList<String> res = h.get(t);
            Toast.makeText(MainActivity.this, "The Job to do now is "+res.get(0), Toast.LENGTH_LONG).show();
            if(res.size()==1)
                h.remove(t);
            else{
                res.remove(0);
                //System.out.println(res);
                h.put(t,res);
                pri.add(t);
            }
        }
    }
    public void onClear(View view){
        Toast.makeText(MainActivity.this,"All jobs are cleared", Toast.LENGTH_SHORT).show();
        h.clear();
        pri.clear();
    }
}
