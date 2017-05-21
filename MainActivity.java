package com.androidstudiolearning.nachiappan.nqueens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
TextView textview,textview1,textview2,textview3,textview4,textview5,textview6,textview7,textview8,textview9,textview10,textview11
        ,textview12,textview13,textview14,textview15;

    public int[][] a=new int[20][20];
    int []x=new int[10];
    int n=4;
    Button solve;
    Button but;
    Button Next;

    int i=0;
    /*create textviews to display the output at last*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview=(TextView)findViewById(R.id.textView2);
        textview1=(TextView)findViewById(R.id.textView4);
        textview2=(TextView)findViewById(R.id.textView3);
        textview3=(TextView)findViewById(R.id.textView);
        textview4=(TextView)findViewById(R.id.textView5);
        textview5=(TextView)findViewById(R.id.textView6);
        textview6=(TextView)findViewById(R.id.textView8);
        textview7=(TextView)findViewById(R.id.textView7);
        textview8=(TextView)findViewById(R.id.textView10);
        textview9=(TextView)findViewById(R.id.textView9);
        textview10=(TextView)findViewById(R.id.textView11);
        textview11=(TextView)findViewById(R.id.textView12);
        textview12=(TextView)findViewById(R.id.textView13);
        textview13=(TextView)findViewById(R.id.textView14);
        textview14=(TextView)findViewById(R.id.textView15);
        textview15=(TextView)findViewById(R.id.textView16);
        but=(Button)findViewById(R.id.button);
but.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        csll();
    }
});
        solve=(Button)findViewById(R.id.button6);
        calldisplay();


        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              callfinish();
            }
        });


    }

public void csll(){
    startActivity(new Intent(this,DisplayActivity.class));
}

    /* first function called when the solve button is pressed it just sends the intial row and then recurses*/
    public void callfinish(){
        int l=0;
        Backtrack(l);
        calldisplay();
    }
    /*Backtrack function is to place the queen in the correct position in a particular row*/
    public void Backtrack(int i){
        int j;

        for(j=0;j<n;j++){
            if(callissafe(i,j)){
                a[i][j]=1;
                x[i]=j;
                calldecission(i);
                break;
            }
        }

        if(j==n){
            callrecursivereplace(i);
        }


    }

    /*Recursive function is to recurse to the previous position and then move the queen to safe position and backtrack the row in
    in which the queen cannot be placed at a safe position at first
     */
    public void callrecursivereplace(int i){

        int y=i-1;
        int z=x[i-1];

        a[y][z]=0;

        while(z<n){

            if(callissafe(y,z+1)){
                if(z+1!=4){
                    a[y][z+1]=1;
                    x[y]=z+1;
                    Backtrack(i);
                    break;
                }
                else{
                    z=n;
                    break;
                }
            }

            else{
                z=z+1;
            }
        }

        i=i-1;

        if(z==n){

            if(i>0){
                int g=i;
                callrecursivereplace(g);
            }
        }
    }


/*decission is the base case for the recursion to stop u recurse till the i becomes equal to the number of rows*/
    public void calldecission(int d){
        if(d<n-1){		d=d+1;
            Backtrack(d);}
    }


/* callisafe is used to check whether the queen can be placed at an appropriate position or not*/
    public Boolean callissafe(int r,int c){

            int row;
            int column;
            int rw;

            for(row=r-1,column=c-1;row>=0 && column >=0;row--,column--){
                if (a[row][column]==1){

                    return false;
                }
            }

            for(row=r-1,column=c+1;row>=0 && column<n;row--,column++){
                if(a[row][column]==1){

                    return false;
                }

            }
            for(rw=0;rw<n;rw++){
                if(a[rw][c]==1){

                    return false;
                }
            }


            return true;

        }
    public void calldisplay() {

        textview.setText((String.valueOf(a[0][0])));
        textview1.setText((String.valueOf(a[0][1])));
        textview2.setText((String.valueOf(a[0][2])));
        textview3.setText((String.valueOf(a[0][3])));
        textview4.setText((String.valueOf(a[1][0])));
        textview5.setText((String.valueOf(a[1][1])));
        textview6.setText((String.valueOf(a[1][2])));
        textview7.setText((String.valueOf(a[1][3])));
        textview8.setText((String.valueOf(a[2][0])));
        textview9.setText((String.valueOf(a[2][1])));
        textview10.setText((String.valueOf(a[2][2])));
        textview11.setText((String.valueOf(a[2][3])));
        textview12.setText((String.valueOf(a[3][0])));
        textview13.setText((String.valueOf(a[3][1])));
        textview14.setText((String.valueOf(a[3][2])));
        textview15.setText((String.valueOf(a[3][3])));
    }
}
