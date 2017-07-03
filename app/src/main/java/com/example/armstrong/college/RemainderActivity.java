package com.example.armstrong.college;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class RemainderActivity extends Activity {

    private ScheduleClient scheduleClient;
    private DatePicker picker;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();
        picker = (DatePicker) findViewById(R.id.scheduleTimePicker);
    }
    public void onDateSelectedButtonClick(View v){

        int day = picker.getDayOfMonth();
        int month = picker.getMonth();
        int year = picker.getYear();
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        scheduleClient.setAlarmForNotification(c);
        Toast.makeText(this, "Notification set for: "+ day +"/"+ (month+1) +"/"+ year, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        if(scheduleClient != null)
            scheduleClient.doUnbindService();
        super.onStop();
    }
}