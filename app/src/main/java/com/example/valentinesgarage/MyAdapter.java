package com.example.valentinesgarage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.full_name.setText(user.getFirst_Name());
        holder.full_name.setText(user.());
        holder.full_name.setText(user.getFirst_Name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Access, Date_of_Birth, Department, Email, full_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            full_name = itemView.findViewById(R.id.info_name);
            Access = itemView.findViewById(R.id.info_role);
            Department = itemView.findViewById(R.id.info_dep);

        }
    }
}