package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Income extends AppCompatActivity {
    EditText editTextAmountIn,editTextDateIn,editTextNoteIn;
    Button buttonCancelIn,buttonSaveIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        editTextAmountIn=findViewById(R.id.editTextAmountIn);
        editTextDateIn=findViewById(R.id.editTextDateIn);
        editTextNoteIn=findViewById(R.id.editTextNoteIn);
    }
}