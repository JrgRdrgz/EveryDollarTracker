package com.example.everydollartracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.audiofx.Virtualizer;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {


    public static final String TAG = "register";
    private TextView registerbutton;
    private EditText editTextname, editTextemail, editTextpassword;
    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth;
    String userID;

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
        editTextname = (EditText) findViewById(R.id.name_id);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextpassword = (EditText) findViewById(R.id.password);

    }

    private void registeruser(){
        String email=editTextemail.getText().toString().trim();
        String name=editTextname.getText().toString().trim();
        String password=editTextpassword.getText().toString().trim();


        if(name.isEmpty()){
            editTextname.setError("This field is required");
            editTextname.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextemail.setError("This field is required");
            editTextemail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextpassword.setError("This field is required");
            editTextpassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextpassword.setError("Password must be at least 7 characters long");
            editTextemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Invalid Email Address");
            editTextemail.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NotNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Users").document(userID);
                            Map<String, Object> newuser= new HashMap<>();
                            newuser.put("Name", name);
                            newuser.put("Email", email);
                            documentReference.set(newuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "User created:" + userID);
                                }
                            });

                            User user = new User(name, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>(){
                                @Override
                                public void onComplete(@NotNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(register.this, "Account Created", Toast.LENGTH_LONG).show();

                                    }else{
                                        Toast.makeText(register.this,"Failed to create account", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(register.this,"Failed to create account", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String emailfromDB = snapshot.child(email).child("email").getValue(String.class);
                    String namefromDB = snapshot.child(name).child("name").getValue(String.class);

                    Intent intent = new Intent(getApplicationContext(), profile_page.class);

                    intent.putExtra("name", namefromDB);
                    intent.putExtra("email", emailfromDB);

                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}