package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class LogIn_Activity extends Activity {
    Button btnLogIn, btnSignUp;
    EditText etxtUsername, etxtPassword;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        etxtUsername = findViewById(R.id.etxtUsername);
        etxtPassword = findViewById(R.id.etxtPassword);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Username", String.valueOf(etxtUsername.getText()));
                Log.d("Password", String.valueOf(etxtPassword.getText()));
                activity = new Intent(LogIn_Activity.this, MainActivity.class);
                startActivity(activity);

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(LogIn_Activity.this, SignUp_Activity.class);
                startActivity(activity);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
