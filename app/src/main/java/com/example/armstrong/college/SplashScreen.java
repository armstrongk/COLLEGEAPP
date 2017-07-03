package com.example.armstrong.college;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

        private ProgressBar mProgress;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Show the splash screen
            setContentView(R.layout.activity_splash_screen);
            mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);

            // Start lengthy operation in a background thread
            new Thread(new Runnable() {
                public void run() {
                    doWork();
                    startApp();
                    finish();
                }
            }).start();
        }

        private void doWork() {
            for (int progress = 0; progress < 100; progress += 10) {
                try {
                    Thread.sleep(1000);
                    mProgress.setProgress(progress);
                } catch (Exception e) {
                    e.printStackTrace();
                    //Timber.e(e.getMessage());
                }
            }
        }

        private void startApp() {
            Intent intent = new Intent(SplashScreen.this, CardViewActivity.class);
            startActivity(intent);
        }
    }