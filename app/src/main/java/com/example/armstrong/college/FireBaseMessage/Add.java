package com.example.armstrong.college.FireBaseMessage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.armstrong.college.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Calendar;

public class Add extends AppCompatActivity {
EditText p,n,pr;
    Button send;
    long count=1;
    public  int c = 1;
    public int last=1;
    public int latest;
String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        n=(EditText)findViewById(R.id.name);
        p=(EditText)findViewById(R.id.pid);
        pr=(EditText)findViewById(R.id.price);
        send=(Button) findViewById(R.id.sen);
        Firebase firebase1=new Firebase(Config.FIREBASE_PRODUCTS_URL4);
        firebase1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Log.d("User key", child.getKey());

                    key=child.getKey();


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase firebase=new Firebase(Config.FIREBASE_PRODUCTS_URL3);
                String name=n.getText().toString().trim();
                String pid=p.getText().toString().trim();
                String price=pr.getText().toString().trim();
                String date=java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                if (n.getText().length()>0 && p.getText().length()>0 && pr.getText().length()>0 ){
                    latest=Integer.parseInt(key.replaceAll("[\\D]", ""));
                    count=latest+1;
                    firebase.child("Post").child("programs").child("program"+count).child("count").setValue(generateId(count));
                    firebase.child("Post").child("programs").child("program"+count).child("name").setValue(n.getText().toString());
                    firebase.child("Post").child("programs").child("program"+count).child("pid").setValue(p.getText().toString());
                    firebase.child("Post").child("programs").child("program"+count).child("price").setValue(pr.getText().toString());
                    firebase.child("Post").child("programs").child("program"+count).child("createdat").setValue(date);
                    firebase.child("Post").child("programs").child("program"+count).child("edittedat").setValue(date);
                }

                if (n.getText().length()>0 && p.getText().length()>0 && pr.getText().length()>0 ){
                    latest=Integer.parseInt(key.replaceAll("[\\D]", ""));
                    count=latest+1;
                    firebase.child("Notifications").child("programs").child("program"+count).child("count").setValue(generateId(count));
                    firebase.child("Notifications").child("programs").child("program"+count).child("name").setValue(n.getText().toString());
                    firebase.child("Notifications").child("programs").child("program"+count).child("pid").setValue(p.getText().toString());
                    firebase.child("Notifications").child("programs").child("program"+count).child("price").setValue(pr.getText().toString());
                    firebase.child("Notifications").child("programs").child("program"+count).child("createdat").setValue(date);
                    firebase.child("Notifications").child("programs").child("program"+count).child("edittedat").setValue(date);
                }
                n.setText("");
                p.setText("");
                pr.setText("");


            }
        });
    }

    public String generateId(long num){
        String id = toString().valueOf(num);
        if (id.length() == 1)
            return "000"+id;
        else if (id.length() == 2)
            return "00"+id;
        else if (id.length() == 3)
            return "0"+id;
        else
            return id;
    }
}
