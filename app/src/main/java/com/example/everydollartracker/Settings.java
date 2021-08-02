package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {

    TextView Email, FullName;
    ImageView imageView;
    Button UpdateImage, Save, Cancel, Remove, Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageView imageView = (ImageView) findViewById(R.id.image_id);

        Email = (TextView) findViewById(R.id.email_id);
        FullName = (TextView) findViewById(R.id.name_id);

        UpdateImage = (Button) findViewById(R.id.imageb_id);
        Save = (Button) findViewById(R.id.save_id);
        Cancel = (Button) findViewById(R.id.cancel_id);
        Remove = (Button) findViewById(R.id.remove_id);
        Logout = (Button) findViewById(R.id.logout_id);

        Cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), App_Page.class);
                startActivity(intent);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });
    }
}