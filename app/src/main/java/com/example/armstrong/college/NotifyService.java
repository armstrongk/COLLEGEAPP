package com.example.armstrong.college;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.armstrong.college.FireBaseMessage.AdminNoticeActivity;

public class NotifyService extends Service {

    /**
     * Class for clients to access
     */
    public class ServiceBinder extends Binder {
        NotifyService getService() {
            return NotifyService.this;
        }
    }

    // Unique id to identify the notification.
    private static final int NOTIFICATION = 123;
    // Name of an intent extra we can use to identify if this service was started to create a notification
    public static final String INTENT_NOTIFY = "com.example.armstrong.college.INTENT_NOTIFY";
    // The system notification manager
    private NotificationManager mNM;

    @Override
    public void onCreate() {
        Log.i("NotifyService", "onCreate()");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        // If this service was started by out AlarmTask intent then we want to show our notification
        if(intent.getBooleanExtra(INTENT_NOTIFY, false))
            showNotification();

        // We don't care if this service is stopped as we have already delivered our notification
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients
    private final IBinder mBinder = (IBinder) new ServiceBinder();

    /**
     * Creates a notification and shows it in the OS drag-down status bar
     */
    private void showNotification() {
        // This is the 'title' of the notification
        CharSequence title = "Alarm!!";
        // This is the icon to use on the notification
        int icon = R.drawable.notifiaction_icon;
        // This is the scrolling text of the notification
        CharSequence text = "check your calender for a remainder.";
        // What time to show on the notification
        long time = System.currentTimeMillis();
        Notification notification = new Notification(icon, text, time);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, AdminNoticeActivity.class), 0);
        notification = builder.setContentIntent(contentIntent)
                .setSmallIcon(icon).setTicker(text).setWhen(time)
                .setAutoCancel(true).setContentTitle(title)
                .setContentText(text).build();
        mNM.notify(NOTIFICATION, notification);


    }
}