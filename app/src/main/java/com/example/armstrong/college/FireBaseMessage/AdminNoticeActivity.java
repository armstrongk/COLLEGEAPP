package com.example.armstrong.college.FireBaseMessage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.armstrong.college.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by armstrong on 7/5/2017.
 */
public class AdminNoticeActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    List<FireBase_Model> fireBase_models;
    private ProgressDialog dialog;
    AdapterNotification adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);

        Firebase.setAndroidContext(this);

        fireBase_models = new ArrayList<>();
        adapter = new AdapterNotification(this, fireBase_models);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView,new RecyclerTouchListener.ClickListener(){

            @Override
            public void onClick(View view, int position) {
                FireBase_Model product =fireBase_models.get(position);
                Intent go = new Intent(AdminNoticeActivity.this, Edit.class);
                go.putExtra("name", fireBase_models.get(position).getName());
                go.putExtra("pid",fireBase_models.get(position).getPid());
                go.putExtra("price",fireBase_models.get(position).getPrice());
                go.putExtra("count",fireBase_models.get(position).getCount());

                startActivity(go);

            }

            @Override
            public void onLongClick(View view, int position) {
                //To at long click.
            }
        }));
        Firebase fire = new Firebase(Config.FIREBASE_PRODUCTS_URL4);
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
                    FireBase_Model fire = postSnapshot.getValue(FireBase_Model.class);
                    fireBase_models.add(fire);
                    adapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
