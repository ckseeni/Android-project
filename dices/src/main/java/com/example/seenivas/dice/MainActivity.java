package com.example.seenivas.dice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int user_Overall_Score=0;
    private int user_Turn_Score=0;
    private int computer_Overall_Score=0;
    private int computer_Turn_Score=0;
    //private String turn = "Your turn score:0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = (TextView)findViewById(R.id.textView);
        String overAll = getString(R.string.overAll);
        overAll = "Your score: 0 Computer score: 0";
        textView.setText(overAll);

        TextView textView1 = (TextView)findViewById(R.id.textView1);
        String turn = getString(R.string.turn);
        turn = "Your turn score:0";
        textView1.setText(turn);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    public void onButtonClick1(View v){
        ImageView imgView = (ImageView)findViewById(R.id.imageView);
        Random rand = new Random();
        int randint = rand.nextInt(6)+1;
        user_Turn_Score = randint;
        user_Overall_Score = user_Overall_Score + user_Turn_Score;
        String imgName = "dice"+user_Turn_Score;
        int id = getResources().getIdentifier(imgName,"drawable",getPackageName());
        imgView.setImageResource(id);

       /* TextView textView = (TextView)findViewById(R.id.textView);
        String overAll = getString(R.string.overAll);
        overAll = "Your score:"+user_Overall_Score+" Computer score:"+computer_Overall_Score;
        textView.setText(overAll);*/

        TextView textView1 = (TextView)findViewById(R.id.textView1);
        String turn = getString(R.string.turn);
        turn = "Your turn score:"+user_Turn_Score;
        textView1.setText(turn);

    }

    public void onButtonClick2(View v){

        TextView textView = (TextView)findViewById(R.id.textView);
        String overAll = getString(R.string.overAll);
        overAll = "Your score:"+user_Overall_Score+" Computer score:"+computer_Overall_Score;
        textView.setText(overAll);

        user_Turn_Score = 0;
        TextView textView1 = (TextView)findViewById(R.id.textView1);
        String turn = getString(R.string.turn);
        turn = "Your turn score:"+user_Turn_Score;
        textView1.setText(turn);

    }

    public void onButtonClick3(View v){
        user_Overall_Score = 0;
        user_Turn_Score = 0;
        computer_Overall_Score = 0;
        computer_Turn_Score = 0;

        TextView textView = (TextView)findViewById(R.id.textView);
        String overAll = getString(R.string.overAll);
        overAll = "Your score:"+user_Overall_Score+" Computer score:"+computer_Overall_Score;
        textView.setText(overAll);

        TextView textView1 = (TextView)findViewById(R.id.textView1);
        String turn = getString(R.string.turn);
        turn = "Your turn score:"+user_Turn_Score;
        textView1.setText(turn);
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
}
