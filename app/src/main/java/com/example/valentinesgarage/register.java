package com.example.valentinesgarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.app.DatePickerDialog;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class register extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton, register;
    private RadioButton member, admin;
    private EditText firstName, surname, phone, email, pass, department, dob;
    private boolean checked, valid;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;

    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        loader = new ProgressDialog(this);

        register = findViewById(R.id.btn_reg);
        member = findViewById(R.id.rb_member);
        admin = findViewById(R.id.rb_admin);
        firstName = findViewById(R.id.et_firstName);
        surname = findViewById(R.id.et_Surname);
        email = findViewById(R.id.et_email2);
        pass = findViewById(R.id.et_password2);
        department = findViewById(R.id.et_dep);
        dob = findViewById(R.id.et_dob);

        checked = ((RadioButton) member).isChecked();

        /**
       register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(name);
                checkField(email);
                checkField(pass);
                checkField(phone);

                if(valid)
                {
                    // start user registration process
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult)
                        {
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(register.getContext(), "Account Created", Toast.LENGTH_SHORT).show();
                            DocumentReference df = fStore.collection("Users").document(Objects.requireNonNull(user).getUid());
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("Full Name", name.getText().toString());
                            userInfo.put("Email address", email.getText().toString());
                            userInfo.put("Phone Number", phone.getText().toString());

                            //Specify if user is admin
                            userInfo.put("isUser", "1");

                            df.set(userInfo);
                            startActivity(new Intent(getApplicationContext(),member_dashboard.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(register.this, "Failed to create Account", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        */

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String firstName_reg = firstName.getText().toString().trim();
                String surname_reg = surname.getText().toString().trim();
                String dob_reg = dob.getText().toString().trim();
                String department_reg = department.getText().toString().trim();
                String email_reg = email.getText().toString().trim();
                String pass_reg = pass.getText().toString().trim();
                String role = onRadioButtonClicked(v);


                if (!checkField(firstName) || !checkField(surname) || !checkField(dob) || !checkField(dob) || !checkField(email) || !checkField(pass) || !checkField(email)) {
                    Toast.makeText(register.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    loader.setMessage("Registration in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    if (member.isChecked()) {
                        fAuth.createUserWithEmailAndPassword(email_reg, pass_reg).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(register.this, member_dashboard.class);
                                    startActivity(intent);
                                    finish();
                                    loader.dismiss();
                                } else {
                                    String error = task.getException().toString();
                                    Toast.makeText(register.this, "Registration failed" + error, Toast.LENGTH_SHORT).show();
                                    loader.dismiss();
                                }
                            }
                        });
                    } else if (admin.isChecked()) {
                        fAuth.createUserWithEmailAndPassword(email_reg, pass_reg).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(register.this, admin_dashboard.class);
                                    startActivity(intent);
                                    finish();
                                    loader.dismiss();
                                } else {
                                    String error = task.getException().toString();
                                    Toast.makeText(register.this, "Registration failed" + error, Toast.LENGTH_SHORT).show();
                                    loader.dismiss();
                                }
                            }
                        });
                    }
                }
            }
        });

    }



    public boolean checkField(EditText txtField)
    {
        if(txtField.getText().toString().isEmpty())
        {
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

    public String onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_member:
                if (checked)
                    return member.getText().toString().trim();
                    break;
            case R.id.rb_admin:
                if (checked)
                    return admin.getText().toString().trim();
                    break;
        }
        return null;
    }
}