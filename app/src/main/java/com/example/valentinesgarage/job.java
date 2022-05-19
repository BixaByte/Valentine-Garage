package com.example.valentinesgarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class job extends AppCompatActivity {

    private AppCompatButton task, dashboard, report, user, cancel, add;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private TextView noJobs;

    private EditText job_text, desc_text, task_text, assign_text;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String onlineUserID;

    private ProgressDialog loader;


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

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), admin_dashboard.class));
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

        job_text = myView.findViewById(R.id.et_title_job);
        desc_text = myView.findViewById(R.id.et_title_desc);
        task_text = myView.findViewById(R.id.et_title_task);
        assign_text = myView.findViewById(R.id.et_title_assign);

        cancel = myView.findViewById(R.id.btn_cancel);
        add = myView.findViewById(R.id.btn_add);

        cancel.setOnClickListener((view -> {dialog.dismiss(); }));

        add.setOnClickListener((view -> {
            String mjob_text = job_text.getText().toString().trim();
            String mdesc_text = desc_text.getText().toString().trim();
            String mtask_text = task_text.getText().toString().trim();
            String massign_text = assign_text.getText().toString().trim();
            String id = reference.push().getKey();
            String date = DateFormat.getDateInstance().format(new Date());
            if (TextUtils.isEmpty(mjob_text)){
                job_text.setError("Job Required");
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

                Model model = new Model(mjob_text,mdesc_text, mtask_text, massign_text, id, date);
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
}
