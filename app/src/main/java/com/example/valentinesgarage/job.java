package com.example.valentinesgarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import static com.firebase.ui.common.Preconditions.assertNonNull;
import static com.firebase.ui.common.Preconditions.assertNull;

import java.text.DateFormat;
import java.util.Date;

public class job extends AppCompatActivity {

    private AppCompatButton task, dashboard, report, user, cancel, add;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private TextView noJobs;

    private EditText client_text, desc_text, task_text, assign_text, client_id_text, vehicleM, vehicleC, no_plate_text, reg_no_text;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String onlineUserID;
    private ProgressDialog loader;
    private String key = "", client, client_id, vModel, vCode, noPlate, regNo, desc, sTask, assign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);


        dashboard = findViewById(R.id.btn_job_db);
        task = findViewById(R.id.btn_job_task);
        user = findViewById(R.id.btn_job_user);
        report = findViewById(R.id.btn_job_report);



        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), admin_dashboard.class));
            }
        });

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), tasks.class));
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), userList.class));
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), report.class));
            }
        });

        toolbar = findViewById(R.id.job_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Jobs");

        recyclerView = findViewById(R.id.rv_job);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        loader = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        onlineUserID = mUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("tasks").child(onlineUserID);

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });
    }

    private void addTask()
    {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View myView = inflater.inflate(R.layout.job_card , null);
        myDialog.setView(myView);

        final AlertDialog dialog = myDialog.create();
        dialog.setCancelable(false);
        dialog.show();

        client_text = myView.findViewById(R.id.et_title_client);
        client_id_text = myView.findViewById(R.id.et_title_client_id);
        vehicleM = myView.findViewById(R.id.et_title_vehicle_model);
        vehicleC = myView.findViewById(R.id.et_title_vehicle_code);
        no_plate_text = myView.findViewById(R.id.et_title_no_plate);
        reg_no_text = myView.findViewById(R.id.et_title_reg_no);
        desc_text = myView.findViewById(R.id.et_title_desc);
        task_text = myView.findViewById(R.id.et_title_task);
        assign_text = myView.findViewById(R.id.et_title_assign);

        cancel = myView.findViewById(R.id.btn_cancel);
        add = myView.findViewById(R.id.btn_add);

        cancel.setOnClickListener((view -> {dialog.dismiss(); }));

        add.setOnClickListener((view -> {
            String mclient_text = client_text.getText().toString().trim();
            String mclient_id_text = client_id_text.getText().toString().trim();
            String mvehicleM = vehicleM.getText().toString().trim();
            String mvehicleC = vehicleC.getText().toString().trim();
            String mno_plate_text = no_plate_text.getText().toString().trim();
            String mreg_no_text = reg_no_text.getText().toString().trim();
            String mdesc_text = desc_text.getText().toString().trim();
            String mtask_text = task_text.getText().toString().trim();
            String massign_text = assign_text.getText().toString().trim();
            String id = reference.push().getKey();
            String date = DateFormat.getDateInstance().format(new Date());
            String status = "0";
            if (TextUtils.isEmpty(mclient_text)){
                client_text.setError("Client Required");
                return;
            }
            if (TextUtils.isEmpty(mclient_text)){
                client_id_text.setError("Client ID Required");
                return;
            }
            if (TextUtils.isEmpty(mclient_text)){
                vehicleM.setError("Enter Vehicle Model Required");
                return;
            }
            if (TextUtils.isEmpty(mclient_text)){
                vehicleC.setError("Enter Vehicle Code Required");
                return;
            }
            if (TextUtils.isEmpty(mclient_text)){
                no_plate_text.setError("Number plate Required");
                return;
            }
            if (TextUtils.isEmpty(mclient_text)){
                reg_no_text.setError("Vehicle registration number Required");
                return;
            }
            if (TextUtils.isEmpty(mtask_text)){
                task_text.setError("Task Required");
                return;
            }
            if (TextUtils.isEmpty(mdesc_text)){
                desc_text.setError("Description Required");
                return;
            }
            if (TextUtils.isEmpty(massign_text)){
                assign_text.setError("Required to assign task to User");
                return;
            } else {
                loader.setMessage("Adding your Data");
                loader.setCanceledOnTouchOutside(false);
                loader.show();

                Model model = new Model(mclient_text, mclient_id_text, mvehicleM, mvehicleC, mno_plate_text, mreg_no_text,mdesc_text, mtask_text, massign_text, id, date, status);
                reference.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(job.this, "Task has been added successfully", Toast.LENGTH_SHORT).show();
                            loader.dismiss();
                        } else {
                            String error = task.getException().toString();
                            Toast.makeText(job.this, "Failed to add task", Toast.LENGTH_SHORT).show();
                            loader.dismiss();
                        }
                    }
                });
            }
            dialog.dismiss();
        }));

        dialog.show();
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
            protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Model model) {
                holder.setDate(model.getDate());
                holder.setClientID(model.getClient_id());
                holder.setClient(model.getClient());
                holder.setVehicleModel(model.getVehicleModel());
                holder.setVehicleCode(model.getVehicleCode());
                holder.setNoPlate(model.getNoPlate());
                holder.setRegNo(model.getNoReg());
                holder.setDesc(model.getDescription());
                holder.setTask(model.getTask());
                holder.setAssign(model.getAssign());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        key = getRef(position).getKey();
                        client = model.getClient();
                        client_id = model.getClient_id();
                        vModel = model.getVehicleModel();
                        vCode = model.getVehicleCode();
                        noPlate = model.getNoPlate();
                        regNo = model.getNoReg();
                        desc = model.getDescription();
                        sTask = model.getTask();
                        assign = model.getAssign();

                        updateTask();

                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieved_jobs,parent, false);
                return new MyViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    //define textviews that will be displayed in retrieved jobs xml
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
            TextView TextView = mView.findViewById(R.id.edit_info_client);
            TextView.setText(client);
        }
        public void setClientID(String clientID){
            TextView TextView = mView.findViewById(R.id.edit_info_client_id);
            TextView.setText(clientID);
        }
        public void setVehicleModel(String vehicleModel){
            TextView TextView = mView.findViewById(R.id.edit_info_vehicleM);
            TextView.setText(vehicleModel);
        }
        public void setVehicleCode(String vehicleCode){
            TextView TextView = mView.findViewById(R.id.edit_info_vehicleC);
            TextView.setText(vehicleCode);
        }
        public void setNoPlate(String noPlate){
            TextView TextView = mView.findViewById(R.id.edit_info_no_plate);
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
            TextView TextView = mView.findViewById(R.id.edit_info_task);
            TextView.setText(task);
        }
        public void setAssign(String assign){
            TextView TextView = mView.findViewById(R.id.edit_info_assign);
            TextView.setText(assign);
        }
    }
    private void updateTask(){
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.update_data, null);
        myDialog.setView(view);

        AlertDialog dialog = myDialog.create();

        EditText  mClient = view.findViewById(R.id.et_edit_client);
        EditText  mClient_id = view.findViewById(R.id.et_edit_client_id);
        EditText  mVmodel = view.findViewById(R.id.et_edit_vehicle_model);
        EditText  mVcode = view.findViewById(R.id.et_edit_vehicle_code);
        EditText  mNo_plate = view.findViewById(R.id.et_edit_no_plate);
        EditText  mReg_no = view.findViewById(R.id.et_edit_reg_no);
        EditText  mDesc = view.findViewById(R.id.et_edit_desc);
        EditText  mTask = view.findViewById(R.id.et_edit_task);
        EditText  mAssign = view.findViewById(R.id.et_edit_assign);

        mClient.setText(client);
        mClient.setSelection(client.length());

        mClient_id.setText(client_id);
        mClient_id.setSelection(client_id.length());

        mVmodel.setText(vModel);
        mVmodel.setSelection(vModel.length());

        mVcode.setText(vCode);
        mVcode.setSelection(vCode.length());

        mNo_plate.setText(noPlate);
        mNo_plate.setSelection(noPlate.length());

        mReg_no.setText(regNo);
        mReg_no.setSelection(regNo.length());

        mDesc.setText(desc);
        mDesc.setSelection(desc.length());

        mTask.setText(sTask);
        mTask.setSelection(sTask.length());

        mAssign.setText(assign);
        mAssign.setSelection(assign.length());

        AppCompatButton delButton = view.findViewById(R.id.btn_delete);
        AppCompatButton saveButton = view.findViewById(R.id.btn_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client = mClient.getText().toString().trim();
                client_id = mClient_id.getText().toString().trim();
                vModel = mVmodel.getText().toString().trim();
                vCode = mVcode.getText().toString().trim();
                noPlate = mNo_plate.getText().toString().trim();
                regNo = mReg_no.getText().toString().trim();
                desc = mDesc.getText().toString().trim();
                sTask = mTask.getText().toString().trim();
                assign = mAssign.getText().toString().trim();
                String status = "0";

                String date = DateFormat.getDateInstance().format(new Date());

                Model model = new Model(client, client_id, vModel, vCode, noPlate, regNo, desc, sTask, assign, key, date, status);

                reference.child(key).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(job.this, "Data has been updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            String err = task.getException().toString();
                            Toast.makeText(job.this, "Update failed" + err, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.dismiss();

            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(job.this, "Data has been deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            String err = task.getException().toString();
                            Toast.makeText(job.this, "Unable to delete job" + err, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
