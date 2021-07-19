package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class register extends AppCompatActivity {

    private TextView registerbutton;
    private EditText editTextname, editTextemail, editTextpassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerbutton = (Button) findViewById(R.id.registerbutton);
        registerbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.registerbutton:
                        registeruser();
                }
            }
        });
        editTextname = (EditText) findViewById(R.id.name);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextpassword = (EditText) findViewById(R.id.password);

    }

    private void registeruser() {
        String email=editTextemail.getText().toString().trim();
        String name=editTextname.getText().toString().trim();
        String password=editTextpassword.getText().toString().trim();

        if(name.isEmpty()){

        }

    }
}