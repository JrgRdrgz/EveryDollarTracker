package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class note_detail extends AppCompatActivity {
    notes_db myDB;
    Button save;
    EditText editT, editD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        editT = (EditText) findViewById(R.id.each_title);
        editD = (EditText) findViewById(R.id.each_detail);
        save = (Button) findViewById(R.id.note_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newT = editT.getText().toString();
                String newD = editD.getText().toString();

                Intent intent = new Intent(getApplicationContext(),note_view.class);
                startActivity(intent);
            }
        });
    }
}