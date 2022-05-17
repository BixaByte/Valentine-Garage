package com.example.valentinesgarage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class login extends AppCompatActivity {
    private EditText phone, password;
    private Button login, register;
    private RadioButton admin, member;
    private boolean valid = true;
    private CardView rl_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.et_phone);
        password = findViewById(R.id.et_password);
        member = findViewById(R.id.rb_member);
        admin = findViewById(R.id.rb_admin);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        checkField(phone);
        checkField(password);

               register.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       startActivity(new Intent(getApplicationContext(), register.class));
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