package com.example.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private int seconds = 0;
    private boolean running=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }


    private void runTimer() {

            final TextView timeView = (TextView)findViewById(R.id.time_view);
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    int hours = seconds/3600;
                    int minutes = (seconds%3600)/60;
                    int secs = seconds%60;
                    String time = String.format(Locale.getDefault(),
                            "%d:%02d:%02d", hours, minutes, secs);
                    timeView.setText(time);
                    if (running) {
                        seconds++;
                    }
                    handler.postDelayed(this, 1000);
                }
            });
    }

    public void start(View view) {
        running=true;
    }

    public void pause(View view) {
        running=false;
    }

    public void reset(View view) {
        running=false;
        seconds=0;
    }
}
