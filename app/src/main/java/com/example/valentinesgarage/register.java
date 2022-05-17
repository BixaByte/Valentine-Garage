package com.example.valentinesgarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class register extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton, register;
    private RadioButton member, admin;
    private EditText name, phone, email, pass;
    private boolean valid;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        register = findViewById(R.id.btn_reg);
        member = findViewById(R.id.rb_member);
        admin = findViewById(R.id.rb_admin);
        name = findViewById(R.id.et_name);
        email = findViewById(R.id.et_email2);
        pass = findViewById(R.id.et_password2);
        phone = findViewById(R.id.et_phone2);


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


    }



    public boolean checkField(EditText txtField)
    {
        if(txtField.getText().toString().isEmpty())
        {
            txtField.setError("Error");
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
}