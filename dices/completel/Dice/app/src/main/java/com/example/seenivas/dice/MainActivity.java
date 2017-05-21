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
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int user_Overall_Score=0;
    private int user_Turn_Score=0;
    private int computer_Overall_Score=0;
    private int computer_Turn_Score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateView();

    }

    public void updateView(){
        TextView textView = (TextView)findViewById(R.id.textView);
        String overAll = getString(R.string.overAll);
        overAll = "Your score:"+user_Overall_Score+" Computer score:"+computer_Overall_Score;
        textView.setText(overAll);

        TextView textView1 = (TextView)findViewById(R.id.textView1);
        String turn = getString(R.string.turn);
        turn = "Your turn score:"+user_Turn_Score;
        textView1.setText(turn);
    }

    public void computerTurn(){
        Random rand = new Random();
        int randint = rand.nextInt(6)+1;
        if(randint==1){
            computer_Turn_Score = 0;
            updateView();
        }
        else {
            computer_Turn_Score = computer_Turn_Score + randint;
            computer_decision();
        }
    }

    public void computer_decision(){
        Random rand = new Random();
        int randint = rand.nextInt(2);
        if(randint==0){
            computer_Overall_Score = computer_Overall_Score + computer_Turn_Score;
            computer_Turn_Score = 0;
            updateView();
            if(computer_Overall_Score>=100){
                Toast.makeText(MainActivity.this,"Computer won!!!",Toast.LENGTH_LONG).show();
                reset();
            }

        }
        else {
            computerTurn();
        }

    }

    public void onButtonClick1(View v){
        ImageView imgView = (ImageView)findViewById(R.id.imageView);
        Random rand = new Random();
        int randint = rand.nextInt(6)+1;
        String imgName = "dice"+randint;
        int id = getResources().getIdentifier(imgName,"drawable",getPackageName());
        imgView.setImageResource(id);

        if(randint==1){
            user_Turn_Score = 0;
            updateView();
            computerTurn();

        }
        else{
            user_Turn_Score = user_Turn_Score + randint;
            updateView();
        }
    }

    public void onButtonClick2(View v){

        user_Overall_Score = user_Overall_Score + user_Turn_Score;
        user_Turn_Score = 0;
        updateView();
        if(user_Overall_Score>=100){
            Toast.makeText(MainActivity.this,"User won!!!",Toast.LENGTH_LONG).show();
            reset();
        }
        else{
            computerTurn();
        }


    }

    public void onButtonClick3(View v){
        user_Overall_Score = 0;
        user_Turn_Score = 0;
        computer_Overall_Score = 0;

       updateView();
    }

    public void reset(){
        user_Overall_Score = 0;
        user_Turn_Score = 0;
        computer_Overall_Score = 0;

        updateView();
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
