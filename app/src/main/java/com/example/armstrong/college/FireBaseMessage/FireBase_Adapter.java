package com.example.armstrong.college.FireBaseMessage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.armstrong.college.R;
import com.example.armstrong.college.UserModel;

import java.util.List;

/**
 * Created by Franc on 10/16/2016.
 */

public class FireBase_Adapter extends RecyclerView.Adapter<FireBase_Adapter.MyViewHolder> {

    private Context mContext;
    private List<UserModel> firebasepostsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pids, names, price;

        public MyViewHolder(View view) {
            super(view);
            names = (TextView) view.findViewById(R.id.namez);
            price = (TextView) view.findViewById(R.id.pricez);

        }
    }

    public FireBase_Adapter(Context mContext, List<UserModel> firebasepostList) {
        this.mContext = mContext;
        this.firebasepostsList = firebasepostList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.firebase_row_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserModel fire = firebasepostsList.get(position);
        holder.names.setText(fire.getFirst_name());
        holder.price.setText(fire.getMobile_number());

    }

    @Override
    public int getItemCount() {
        return firebasepostsList.size();
    }

}
