package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Expense extends AppCompatActivity {
    EditText editTextAmountEx,editTextDateEx,editTextNoteEx;
    Button buttonCancelEx,buttonSaveEx;
    TextView selectEx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        editTextAmountEx=findViewById(R.id.editTextAmountEx);
        editTextDateEx=findViewById(R.id.editTextDateEx);
        editTextNoteEx=findViewById(R.id.editTextNoteEx);

    }
    public void se1Ex (View view) {
        selectEx=findViewById(R.id.selectEx);
        selectEx.setText("SAVINGS");
        Toast.makeText(this, "SAVINGS", Toast.LENGTH_SHORT).show();
    }
    public void se2Ex (View view) {
        selectEx=findViewById(R.id.selectEx);
        selectEx.setText("NEEDS");
        Toast.makeText(this, "NEEDS", Toast.LENGTH_SHORT).show();
    }
    public void se3Ex (View view) {
        selectEx=findViewById(R.id.selectEx);
        selectEx.setText("WANTS");
        Toast.makeText(this, "WANTS", Toast.LENGTH_SHORT).show();
    }


    public void goToHomeE (View view) {
        startActivity(new Intent(getApplicationContext(), Dashboard.class ));
        Toast.makeText(this, "Go To Homepage", Toast.LENGTH_SHORT).show();
    }

    public void addNewExpenses (View view) {
        EditText editTextAmountEx,editTextDateEx,editTextNoteEx;
        editTextAmountEx=findViewById(R.id.editTextAmountEx);
        editTextDateEx=findViewById(R.id.editTextDateEx);
        editTextNoteEx=findViewById(R.id.editTextNoteEx);
        selectEx=findViewById(R.id.selectEx);

        String amountSt = editTextAmountEx.getText().toString().trim();
        String date = editTextDateEx.getText().toString().trim();
        String note = editTextNoteEx.getText().toString().trim();
        String source = selectEx.getText().toString().trim();
        String type="EXPENSES";


        if (amountSt.isEmpty()) {
            editTextAmountEx.setError("This field is required");
            return;
        }
        double amount = Double.parseDouble(amountSt);
        if (date.isEmpty()) {
            editTextDateEx.setError("This field is required");
            return;
        }
        if (source.isEmpty()) {
            Toast.makeText(this, "Select an Excome source", Toast.LENGTH_LONG).show();
            return;
        }

        if (note.isEmpty()) {
            editTextNoteEx.setText("NONE");
            Toast.makeText(this, "set note to 'NONE'", Toast.LENGTH_SHORT).show();
        }

        /*Dashboard.addInOrEx(amount,type,date,source, note);
        Toast.makeText(this, "Added new Expenses", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), Dashboard.class ));// back to home after done*/
    }
}
