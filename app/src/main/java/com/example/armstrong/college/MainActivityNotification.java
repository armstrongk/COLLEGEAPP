package com.example.armstrong.college;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.armstrong.college.messenger.ServiceScheduler;

public class MainActivityNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notification);

        ServiceScheduler serviceScheduler = ServiceScheduler.newInstance(this);
        if (serviceScheduler.isEnabled()) {
            serviceScheduler.startService();
        }
    }
}
