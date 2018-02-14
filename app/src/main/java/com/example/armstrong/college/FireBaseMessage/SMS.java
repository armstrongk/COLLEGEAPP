package com.example.armstrong.college.FireBaseMessage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;

import com.example.armstrong.college.R;
import com.example.armstrong.college.SmsActivity;
import com.example.armstrong.college.UserModel;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SMS extends AppCompatActivity {
    RecyclerView recyclerView;
    List<UserModel> fireBase_models;
    private ProgressDialog dialog;
    FireBase_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms2);

        Firebase.setAndroidContext(this);

        fireBase_models = new ArrayList<>();
        adapter = new FireBase_Adapter(this, fireBase_models);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView,new RecyclerTouchListener.ClickListener(){

            @Override
            public void onClick(View view, int position) {
                UserModel product =fireBase_models.get(position);
                sendSMS(product.getMobile_number(),"Type you message here?");
                Firebase m_objFireBaseRef = new Firebase(Config.FIREBASE_PRODUCTS_URL3);
                Firebase objRef = m_objFireBaseRef.child("users");
//                objRef.child(product.getUser_id()).child("schedule").setValue("usa");
            }

            @Override
            public void onLongClick(View view, int position) {
                //To at long click.
            }
        }));
        Firebase fire = new Firebase(Config.FIREBASE_PRODUCTS_URL);
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading data wait or check internet connection.....");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        fire.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(int i=fireBase_models.size()-1; i>=0; i--)
                    fireBase_models.remove(i);
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    UserModel fire = postSnapshot.getValue(UserModel.class);
                    fireBase_models.add(fire);
                    adapter.notifyDataSetChanged();
                   // sendSMS("0757522074","assignemnt1");
                }
                dialog.dismiss();

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    private void sendSMS(String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", message);
        startActivity(intent);

    }
}
