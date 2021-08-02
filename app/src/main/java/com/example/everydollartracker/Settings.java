package com.example.everydollartracker;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class Settings extends AppCompatActivity
{

    EditText FullName, Email;
    ImageView imageView;
    Button Save, Cancel, Remove, Logout, Updateimage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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

        String emailVal = Email.getText().toString().trim();
        String FullNameVal = FullName.getText().toString().trim();

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

        Remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if (task.isSuccessful())
                                {
                                    Log.d(TAG, "User account deleted.");
                                }
                            }
                        });
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
        {
            String name = user.getDisplayName();
            String email = user.getEmail();
            FullName.setText(name);
            Email.setText(email);

        }
        else
        {

        }



    }
}