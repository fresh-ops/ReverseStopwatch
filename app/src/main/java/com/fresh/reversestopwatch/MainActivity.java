package com.fresh.reversestopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fresh.reversestopwatch.time.Stopwatch;

public class MainActivity extends AppCompatActivity {
    private TextView timeOut;
    private Button buttonStart;
    private Button buttonPause;
    private Button buttonReset;
    private Button buttonReverse;
    private Stopwatch stopwatch = new Stopwatch();

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeOut = findViewById(R.id.timeOut);
        buttonStart = findViewById(R.id.buttonStart);
        buttonPause = findViewById(R.id.buttonPause);
        buttonReset = findViewById(R.id.buttonReset);
        buttonReverse = findViewById(R.id.buttonReverse);

        buttonStart.setOnClickListener(listener);
        buttonPause.setOnClickListener(listener);
        buttonReset.setOnClickListener(listener);
        buttonReverse.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonStart:
                    stopwatch.start();
                    handler.post(updateTimerThread);
                    break;
                case R.id.buttonPause:
                    stopwatch.pause();
                    handler.removeCallbacks(updateTimerThread);
                    break;
                case R.id.buttonReset:
                    stopwatch.reset();
                    timeOut.setText("00.000");
                    break;
                case R.id.buttonReverse:
                    stopwatch.reverse();
                    break;
            }
        }
    };

    private final Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            stopwatch.updateTime();
            if (stopwatch.getTime() < 0) {
                buttonReset.callOnClick();
                return;
            }
            timeOut.setText(stopwatch.toString());
            handler.post(updateTimerThread);
        }
    };
}