package com.example.everydollartracker;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;

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

        Updateimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseStorage storage = FirebaseStorage.getInstance();

            }
        });

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
            for (UserInfo profile : user.getProviderData())
            {
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                FullName.setText(name);
                Email.setText(email);

            }


        }
        else
        {

        }

        Save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String FullNameVal = FullName.getEditableText().toString().trim();
                String EmailVal = Email.getEditableText().toString().trim();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(FullNameVal).build();
                FullName.setText(FullNameVal);

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User profile updated.");
                                }
                            }
                        });

                if (FullNameVal.isEmpty())
                {
                    FullName.setError("Enter Your Name");
                    FullName.requestFocus();
                    return;
                }

                if (EmailVal.isEmpty())
                {
                    Email.setError("Enter Your Email");
                    Email.requestFocus();
                    return;
                }




                Intent intent = new Intent(getApplicationContext(), App_Page.class);
                startActivity(intent);

            }
        });



    }
}