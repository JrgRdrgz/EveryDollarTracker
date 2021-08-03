package com.example.everydollartracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class login extends AppCompatActivity implements View.OnClickListener {

    TextView register;
    private EditText editTextemail, editTextpassword;
    private Button loginbutton;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        loginbutton = (Button) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(this);

        editTextemail = (EditText) findViewById(R.id.email);
        editTextpassword = (EditText) findViewById(R.id.password);

        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.register:
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
                break;

            case R.id.loginbutton:
                loginuser();
                break;
        }

    }

    private void loginuser() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextemail.setError("This field is required");
            editTextemail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextpassword.setError("This field is required");
            editTextpassword.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Invalid Email Address");
            editTextemail.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(login.this, App_Page.class));
                }else{
                    Toast.makeText(login.this, "Unable to Login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}