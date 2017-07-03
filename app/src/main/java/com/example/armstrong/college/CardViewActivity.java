package com.example.armstrong.college;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;


public class CardViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
           public void onItemClick(int position, View v) {
                    if(position==0) {
                        Intent intent = new Intent(CardViewActivity.this, GuestUserActivity.class);
                        startActivity(intent);
                    }
                    else if (position ==1){
                        Intent intent = new Intent(CardViewActivity.this, LoginActivity.class);
                        intent.putExtra("ItemPosition", position);
                        startActivity(intent);
                    }
                    else if (position==2){
                        Intent intent = new Intent(CardViewActivity.this, LoginActivityAdmin.class);
                        intent.putExtra("ItemPosition", position);
                        startActivity(intent);
                    }


            }
        });
    }



    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 1; index++) {
            DataObject obj = new DataObject("LECTURER", R.drawable.guest);
            DataObject obj1 = new DataObject("STUDENT ",R.drawable.student);
            DataObject obj2 = new DataObject("GUEST USER ",R.drawable.lecturere);
            results.add(index, obj);
            results.add(index, obj1);
            results.add(index, obj2);
        }
        return results;
    }

}