package com.example.armstrong.college;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminRecylerCardView extends AppCompatActivity {

    //recyclerview objects
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    //model object for our list data
    private List<MyList> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_recyler_card_view);

        //initializing views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        //loading list view item with this function
        loadRecyclerViewItem();
    }

    private void loadRecyclerViewItem() {
        //you can fetch the data from server or some apis
        //for this tutorial I am adding some dummy data directly
        for (int i = 1; i <= 1; i++) {
            MyList myList1 = new MyList(
                    "SEND PUSH NOTIFICATION ",
                    "push notification can be sent either to selected number or as a broadcast.");
            MyList myList2 = new MyList(
                    "SEND SMS NOTIFICATION ",
                    "sends brodcast using SMS services .");
            MyList myList3 = new MyList(
                    "DELETE NOTICE  " ,
                    "Remove expired notices.");
            MyList myList4 = new MyList(
                    "REMOVE USER " ,
                    "remove unqualified students.");
            MyList myList5 = new MyList(
                    "VIEW PROFILE " ,
                    "admin views his profile and students profile.");

           list.add(myList1);
            list.add(myList2);
            list.add(myList3);
            list.add(myList4);
            list.add(myList5);
        }

        adapter = new CustomAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }

}
