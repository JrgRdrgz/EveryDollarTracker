package com.example.everydollartracker;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class Settings extends AppCompatActivity
{

    EditText FullName, Email;
    ImageView imageView;
    Button Save, Cancel, Remove, Logout, Updateimage, Update;
    StorageReference storageReference;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //FullName = findViewById(R.id.fullname_id);
       // Email = findViewById(R.id.email_id);
        imageView = findViewById(R.id.image_id);
        Save = findViewById(R.id.save_id);
        Cancel = findViewById(R.id.cancel_id);
        Remove = findViewById(R.id.remove_id);
        Logout = findViewById(R.id.logout_id);
        Updateimage = findViewById(R.id.imageb_id);
        Update = findViewById(R.id.up_id);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
        {

                String name = user.getDisplayName();
                String email = user.getEmail();
                FullName.setText(name);
                Email.setText(email);


        }
        else
        {}



        fAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileR = storageReference.child("Users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
        profileR.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageView);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {

            String FullNameVal = FullName.getEditableText().toString();
            String EmailVal = Email.getEditableText().toString();
            @Override
            public void onClick(View v) {
        //        Intent i = new Intent(v.getContext(), Update_C.class);
        //        i.putExtra("FullNameKey", FullNameVal);
        //        i.putExtra("EmailKey", EmailVal);
        //        startActivity(i);
            }
        });

        Updateimage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                Intent OpenGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(OpenGallery, 1);


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

        Save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String FullNameVal = FullName.getEditableText().toString();
                String EmailVal = Email.getEditableText().toString();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(FullNameVal).build();
                FullName.setText(FullNameVal);

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User name updated.");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Uri imageUri = data.getData();
                //imageView.setImageURI(imageUri);
                uploadImagetoFirebase(imageUri);

            }
        }
    }

    private void uploadImagetoFirebase(Uri imageUri) {
        StorageReference file = storageReference.child("Users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
        file.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(imageView);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Settings.this, "Failed to Upload Image", Toast.LENGTH_SHORT).show();
            }
        });
    }

}