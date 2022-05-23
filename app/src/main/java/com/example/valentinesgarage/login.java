package com.example.valentinesgarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private EditText email, password;
    private Button login, register;
    private RadioButton admin, member;
    private boolean valid = true;
    private CardView rl_login;
    private FirebaseAuth fAuth;
    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_phone);
        password = findViewById(R.id.et_password);
        member = findViewById(R.id.rb_member);
        admin = findViewById(R.id.rb_admin);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        fAuth = FirebaseAuth.getInstance();
        loader = new ProgressDialog(this);



               register.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       startActivity(new Intent(getApplicationContext(), register.class));
                   }
               });

               login.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       String login_info = email.getText().toString().trim();
                       String pass_info = password.getText().toString().trim();

                       if (!checkField(email) || !checkField(password))
                       {
                           Toast.makeText(login.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           loader.setMessage("Login in progress");
                           loader.setCanceledOnTouchOutside(false);
                           loader.show();

                           if (member.isChecked()) {
                               fAuth.signInWithEmailAndPassword(login_info, pass_info).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {

                                       if (task.isSuccessful()) {
                                           Intent intent = new Intent(login.this, member_dashboard.class);
                                           startActivity(intent);
                                           finish();
                                           loader.dismiss();
                                       } else {
                                           String error = task.getException().toString();
                                           Toast.makeText(login.this, "Registration failed" + error, Toast.LENGTH_SHORT).show();
                                           loader.dismiss();
                                       }
                                   }
                               });
                           } else if (admin.isChecked()) {
                               fAuth.signInWithEmailAndPassword(login_info, pass_info).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {

                                       if (task.isSuccessful()) {
                                           Intent intent = new Intent(login.this, admin_dashboard.class);
                                           startActivity(intent);
                                           finish();
                                           loader.dismiss();
                                       } else {
                                           String error = task.getException().toString();
                                           Toast.makeText(login.this, "Registration failed" + error, Toast.LENGTH_SHORT).show();
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
}