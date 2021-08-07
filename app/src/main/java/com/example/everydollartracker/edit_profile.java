package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class edit_profile extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText updateemail, updatepass, updatefullname;
    Button UpdateB, Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        updateemail = findViewById(R.id.updateemail_id);
        updatepass = findViewById(R.id.updatepass_id);
        updatefullname = findViewById(R.id.updatefullname_id);
        UpdateB = findViewById(R.id.updateB_id);
        Cancel = findViewById(R.id.cancelB_id);

        Intent data = getIntent();

        String fullName = data.getStringExtra("FullNameKey");
        String email = data.getStringExtra("EmailKey");

        updatefullname.setText(fullName);
        updateemail.setText(email);


        UpdateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FullNameVal = updatefullname.getEditableText().toString();
                String EmailVal = updateemail.getEditableText().toString();

                if (FullNameVal.isEmpty())
                {
                    updatefullname.setError("Enter Your Name");
                    updatefullname.requestFocus();
                    return;
                }

                if (EmailVal.isEmpty())
                {
                    updateemail.setError("Enter Your Email");
                    updateemail.requestFocus();
                    return;
                }

            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }
        });



    }

}