package com.example.seenivas.magicsquare;

import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;



public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        magicsquare();

    }

    public void magicsquare() {
        int i, j, k, n = 4, t;
        int A[][] = new int[n][n];


        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                A[i][j] = 0;
            }
        }

        if (n % 2 != 0) {
            i = 0;
            j = n / 2;
            k = 1;
            while (k <= n * n) {
                A[i][j] = k++;
                i--;
                j++;
                if (i < 0 && j > n - 1) {
                    i = i + 2;
                    j--;
                }
                if (i < 0)
                    i = n - 1;
                if (j > n - 1) j = 0;
                if (A[i][j] > 0) {
                    i = i + 2;
                    j--;
                }
            }
        } else {
            k = 1;

            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    A[i][j] = k++;
                }
            }
            j = n - 1;
            for (i = 0; i < n / 2; i++) {

                t = A[i][i];
                A[i][i] = A[j][j];
                A[j][j] = t;
                t = A[i][j];
                A[i][j] = A[j][i];
                A[j][i] = t;
                j--;
            }
        }
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(String.valueOf(A[0][0]));
        TextView textView2= (TextView)findViewById(R.id.textView2);
        textView2.setText(String.valueOf(A[0][1]));
        TextView textView3 = (TextView)findViewById(R.id.textView3);
        textView3.setText(String.valueOf(A[0][2]));
        TextView textView4 = (TextView)findViewById(R.id.textView4);
        textView4.setText(String.valueOf(A[0][3]));
        TextView textView5 = (TextView)findViewById(R.id.textView5);
        textView5.setText(String.valueOf(A[1][0]));
        TextView textView6 = (TextView)findViewById(R.id.textView6);
        textView6.setText(String.valueOf(A[1][1]));
        TextView textView7 = (TextView)findViewById(R.id.textView7);
        textView7.setText(String.valueOf(A[1][2]));
        TextView textView8 = (TextView)findViewById(R.id.textView8);
        textView8.setText(String.valueOf(A[1][3]));
        TextView textView9 = (TextView)findViewById(R.id.textView9);
        textView9.setText(String.valueOf(A[2][0]));
        TextView textView10 = (TextView)findViewById(R.id.textView10);
        textView10.setText(String.valueOf(A[2][1]));
        TextView textView11 = (TextView)findViewById(R.id.textView11);
        textView11.setText(String.valueOf(A[2][2]));
        TextView textView12= (TextView)findViewById(R.id.textView12);
        textView12.setText(String.valueOf(A[2][3]));
        TextView textView13 = (TextView)findViewById(R.id.textView13);
        textView13.setText(String.valueOf(A[3][0]));
        TextView textView14 = (TextView)findViewById(R.id.textView14);
        textView14.setText(String.valueOf(A[3][1]));
        TextView textView15 = (TextView)findViewById(R.id.textView15);
        textView15.setText(String.valueOf(A[3][2]));
        TextView textView16= (TextView)findViewById(R.id.textView16);
        textView16.setText(String.valueOf(A[3][3]));
    }

}