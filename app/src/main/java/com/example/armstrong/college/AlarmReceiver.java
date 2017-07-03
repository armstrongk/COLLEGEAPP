package com.example.armstrong.college;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.example.armstrong.college.activity1.MainActivity;


/**
 * Created by Franc on 10/25/2016.
 */

public class AlarmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Received alarm");
        generateNotification(context);
    }

    public void generateNotification(Context context){
        NotificationManager nManager;
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                .setContentTitle("Poultry Alert")
                .setContentText("Activity pending Tomorrow")
                .setSmallIcon(R.mipmap.ic_launcher);
        Intent intent = new Intent(context, MainActivity.class);
        TaskStackBuilder tb = TaskStackBuilder.create(context);
        tb.addParentStack(MainActivity.class);
        tb.addNextIntent(intent);
        PendingIntent pi = tb.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        nBuilder.setContentIntent(pi);
        long[] v = {500,3000};
        nBuilder.setVibrate(v);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        nBuilder.setSound(uri);
        nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(33, nBuilder.build());
    }
}
