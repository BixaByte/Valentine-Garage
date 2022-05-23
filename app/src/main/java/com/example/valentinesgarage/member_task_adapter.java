package com.example.valentinesgarage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;

public class member_task_adapter extends RecyclerView.Adapter<member_task_adapter.MyViewHolder>{

    Context context;

    ArrayList<Model> list;

    public member_task_adapter(Context context, ArrayList<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_list_member, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model task = list.get(position);
        holder.client.setText("Client: " +  task.getClient());
        holder.assign.setText("Assigned to: " + task.getAssign());
        holder.vehicleModel.setText("Vehicle Model: " + task.getVehicleModel());
        holder.noPlate.setText("Number Plate: " +task.getNoPlate());
        holder.task.setText(task.getTask());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView client, assign, vehicleModel, noPlate;
        MaterialCheckBox task;

        public MyViewHolder(@NonNull View taskView) {
            super(taskView);

            client = itemView.findViewById(R.id.task_client_tv);
            assign = itemView.findViewById(R.id.task_assign_tv);
            vehicleModel = itemView.findViewById(R.id.task_model_tv);
            noPlate = itemView.findViewById(R.id.task_noPlate);
            task = itemView.findViewById(R.id.task_checkbox);
        }
    }
}
