package com.example.valentinesgarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class tasks_member extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private DatabaseReference database;
    private String onlineUserID;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    member_task_adapter myAdapter;
    ArrayList<Model> list;

    private AppCompatButton db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_member);

        db = findViewById(R.id.btn_taskM_db);

        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), member_dashboard.class));
            }
        });

        recyclerView = findViewById(R.id.rv_tasks_members);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        onlineUserID = mUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("tasks").child(onlineUserID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
/**
        list = new ArrayList<>();
        myAdapter = new member_task_adapter(this, list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Model task = dataSnapshot.getValue(Model.class);
                    list.add(task);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        */



    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        //Methods used to replace text views with database info
        public void setDate(String date){
            TextView TextView = mView.findViewById(R.id.edit_info_date);
            TextView.setText(date);
        }
        public void setClient(String client){
            TextView TextView = mView.findViewById(R.id.task_client_tv);
            TextView.setText(client);
        }
        public void setClientID(String clientID){
            TextView TextView = mView.findViewById(R.id.edit_info_client_id);
            TextView.setText(clientID);
        }
        public void setVehicleModel(String vehicleModel){
            TextView TextView = mView.findViewById(R.id.task_model_tv);
            TextView.setText(vehicleModel);
        }
        public void setVehicleCode(String vehicleCode){
            TextView TextView = mView.findViewById(R.id.edit_info_vehicleC);
            TextView.setText(vehicleCode);
        }
        public void setNoPlate(String noPlate){
            TextView TextView = mView.findViewById(R.id.task_noPlate);
            TextView.setText(noPlate);
        }
        public void setRegNo(String regNo){
            TextView TextView = mView.findViewById(R.id.edit_info_reg_no);
            TextView.setText(regNo);
        }
        public void setDesc(String desc){
            TextView TextView = mView.findViewById(R.id.edit_info_desc);
            TextView.setText(desc);
        }
        public void setTask(String task){
            TextView TextView = mView.findViewById(R.id.task_checkbox);
            TextView.setText(task);
        }
        public void setAssign(String assign){
            TextView TextView = mView.findViewById(R.id.task_assign_tv);
            TextView.setText(assign);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        //display stored jobs on job view
        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
                .setQuery(reference, Model.class)
                .build();

        FirebaseRecyclerAdapter<Model, MyViewHolder> adapter = new FirebaseRecyclerAdapter<Model, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Model model) {
                //holder.setDate(model.getDate());
                //holder.setClientID(model.getClient_id());
                holder.setClient(model.getClient());
                holder.setVehicleModel(model.getVehicleModel());
                //holder.setVehicleCode(model.getVehicleCode());
                holder.setNoPlate(model.getNoPlate());
                //holder.setRegNo(model.getNoReg());
                //holder.setDesc(model.getDescription());
                holder.setTask(model.getTask());
                holder.setAssign(model.getAssign());

            }


            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_member,parent, false);
                return new MyViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

}