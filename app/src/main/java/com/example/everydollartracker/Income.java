package com.example.everydollartracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.everydollartracker.R.id.editTextAmountIn;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Income extends AppCompatActivity {
    private static final String TAG = "Income";
    private FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
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
        startActivity(new Intent(getApplicationContext(), Dashboard.class ));
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
        String type="INCOME";


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

        Map<String, Object> incomeToSave = new HashMap<>();
        incomeToSave.put("Amount", amountSt);
        incomeToSave.put("Date", date);
        incomeToSave.put("Type", type);
        incomeToSave.put("Source", source);
        incomeToSave.put("Note", note);
        String uid=user.getUid();
        DocumentReference ref = db.collection("users").document(uid).collection("Income").document();
        String docID=ref.getId();
        db.collection("users").document(uid).collection("income").add(incomeToSave).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Income added");
            }
        });
        startActivity(new Intent(getApplicationContext(), App_Page.class ));// back to home after done
    }


}
