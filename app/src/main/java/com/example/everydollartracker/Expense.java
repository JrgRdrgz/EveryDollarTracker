package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Expense extends AppCompatActivity {
    private DatabaseReference totalIncome;
    private FirebaseAuth mAuth;
    Spinner expensespinner = findViewById(R.id.expensespinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.expensecategories, android.R.layout.simple_spinner_dropdown_item);
}
