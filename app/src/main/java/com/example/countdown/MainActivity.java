package com.example.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    static final long START_TIME_IN_MILLIS = 600000;
    TextView tv;
    Button b1,b2;
    CountDownTimer cdt;
    boolean timerRunning;
    long mTimeLeftInMills = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (timerRunning){
                  pauseTimer();
              }
              else{
                 startTimer();
              }
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               stopTimer();
            }
        });
        updateCountDownText();
    }

     void startTimer(){
        cdt = new CountDownTimer(mTimeLeftInMills,1000) {
            @Override
            public void onTick(long l) {
               mTimeLeftInMills = l;
               updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                b1.setText("Start");
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.VISIBLE);
            }
        }.start();
        timerRunning = true;
        b1.setText("Pause");
        b2.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer(){
      cdt.cancel();
      timerRunning = false;
      b1.setText("Start");
      b2.setVisibility(View.VISIBLE);
    }

    private void stopTimer(){
       mTimeLeftInMills = START_TIME_IN_MILLIS;
       updateCountDownText();
       b2.setVisibility(View.INVISIBLE) ;
       b2.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText(){
        int minutes = (int) mTimeLeftInMills / 1000 / 60;
        int seconds = (int) mTimeLeftInMills / 1000 % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        tv.setText(timeLeftFormatted);
    }
}