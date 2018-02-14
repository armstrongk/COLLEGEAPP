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
 * Created by armstrong on 7/5/2017.
 */
public class AdapterNotification  extends RecyclerView.Adapter<AdapterNotification.MyViewHolder>{

    private Context mContext;
    private List<FireBase_Model> firebasepostsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView names, price;

        public MyViewHolder(View view) {
            super(view);
            names = (TextView) view.findViewById(R.id.namez);
            price = (TextView) view.findViewById(R.id.pricez);
        }
    }
    public AdapterNotification(Context mContext, List<FireBase_Model> firebasepostList) {
        this.mContext = mContext;
        this.firebasepostsList = firebasepostList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FireBase_Model fire = firebasepostsList.get(position);
        holder.names.setText(fire.getName());
        holder.price.setText(fire.getPid());

    }

    @Override
    public int getItemCount() {
        return firebasepostsList.size();
    }

}
