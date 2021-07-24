package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.everydollartracker.R.id.editTextAmountIn;

public class Income extends AppCompatActivity {
    EditText editTextAmountIn,editTextDateIn,editTextNoteIn;
    Button buttonCancelIn,buttonSaveIn;
    TextView selectIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        editTextAmountIn=findViewById(R.id.editTextAmountIn);
        editTextDateIn=findViewById(R.id.editTextDateIn);
        editTextNoteIn=findViewById(R.id.editTextNoteIn);

    }
    public void se1In (View view) {
        selectIn=findViewById(R.id.selectIn);
        selectIn.setText("SALARY");
        Toast.makeText(this, "SALARY", Toast.LENGTH_SHORT).show();
    }
    public void se2In (View view) {
        selectIn=findViewById(R.id.selectIn);
        selectIn.setText("BONUS");
        Toast.makeText(this, "BONUS", Toast.LENGTH_SHORT).show();
    }
    public void se3In (View view) {
        selectIn=findViewById(R.id.selectIn);
        selectIn.setText("ALLOWANCE");
        Toast.makeText(this, "ALLOWANCE", Toast.LENGTH_SHORT).show();
    }


    public void goToHome (View view) {
        startActivity(new Intent(getApplicationContext(), homepage.class ));
        Toast.makeText(this, "Go To Homepage", Toast.LENGTH_SHORT).show();
    }

    public void addNewIncome (View view) {
        EditText editTextAmountIn,editTextDateIn,editTextNoteIn;
        editTextAmountIn=findViewById(R.id.editTextAmountIn);
        editTextDateIn=findViewById(R.id.editTextDateIn);
        editTextNoteIn=findViewById(R.id.editTextNoteIn);
        selectIn=findViewById(R.id.selectIn);

        String amountSt = editTextAmountIn.getText().toString().trim();
        String date = editTextDateIn.getText().toString().trim();
        String note = editTextNoteIn.getText().toString().trim();
        String source = selectIn.getText().toString().trim();


        if (amountSt.isEmpty()) {
            editTextAmountIn.setError("This field is required");
            return;
        }
        double amount = Double.parseDouble(amountSt);
        if (date.isEmpty()) {
            editTextDateIn.setError("This field is required");
            return;
        }
        if (source.isEmpty()) {
            Toast.makeText(this, "Select an Income source", Toast.LENGTH_LONG).show();
            return;
        }

        if (note.isEmpty()) {
            editTextNoteIn.setText("NONE");
            Toast.makeText(this, "set note to 'NONE'", Toast.LENGTH_SHORT).show();
        }

        User.addIncome(amount,date,source, note);
        Toast.makeText(Income.this, "Added new income", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), homepage.class ));// back to home after done
    }


}
