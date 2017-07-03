package com.example.armstrong.college;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentRecyclerView extends AppCompatActivity {

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
                    "VIEW NOTIFICATIONS ",
                    "these are real time notices sent from admin");
            MyList myList2 = new MyList(
                    " NEWS AND UPDATES",
                    "these are from the college webste");
            MyList myList3 = new MyList(
                    "SET REMINDER  " ,
                    "using alarm");
            MyList myList4 = new MyList(
                    "DELETE NOTICES " ,
                    "delete expired notices");
            MyList myList5 = new MyList(
                    "SEARCH NOTICE " ,
                    "incase of a specific notice");
            MyList myList6 = new MyList(
                    "VIEW PROFILE " ,
                    "views and can edit his or her profile");

            list.add(myList1);
            list.add(myList2);
            list.add(myList3);
            list.add(myList4);
            list.add(myList5);
            list.add(myList6);
        }

        adapter = new CustomAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }

}
