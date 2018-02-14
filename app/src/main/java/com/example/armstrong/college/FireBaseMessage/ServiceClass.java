package com.example.armstrong.college.FireBaseMessage;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import com.example.armstrong.college.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by armstrong on 7/4/2017.
 */
public class ServiceClass extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         Firebase.setAndroidContext(this);
        //Creating a firebase object
        Firebase firebase = new Firebase(Config.FIREBASE_PRODUCTS_URL2);
        Firebase firebase2 = new Firebase(Config.FIREBASE_PRODUCTS_URL4);
        //Adding a valueevent listener to firebase
        //this will help us to  track the value changes on firebase
        firebase2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                showNotification("New notice has been posited");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                showNotification(" Message Has Been Editted");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                showNotification("The Data Has been removed");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                 showNotification("New notice has been added");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                 showNotification("New message");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                showNotification("notice Has been removed");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
     return START_STICKY;
    }

    private void showNotification(String msg){
        //Creating a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.cocis);
        Intent intent = new Intent(ServiceClass.this, AdminNoticeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notifiaction_icon));
        builder.setContentTitle("College App");
        builder.setContentText(msg);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());


    }
}
