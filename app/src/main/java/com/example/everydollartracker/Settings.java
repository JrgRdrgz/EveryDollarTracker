package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {

    EditText FullName, Email;
    ImageView imageView;
    Button Save, Cancel, Remove, Logout, Updateimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        FullName = findViewById(R.id.fullname_id);
        Email = findViewById(R.id.email_id);
        imageView = findViewById(R.id.image_id);
        Save = findViewById(R.id.save_id);
        Cancel = findViewById(R.id.cancel_id);
        Remove = findViewById(R.id.remove_id);
        Logout = findViewById(R.id.logout_id);
        Updateimage = findViewById(R.id.imageb_id);

        Cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), App_Page.class);
                startActivity(intent);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });

    }
}