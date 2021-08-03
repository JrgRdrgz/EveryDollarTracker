package com.example.everydollartracker;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import static com.example.everydollartracker.R.id.sourcespinner;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class Income extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseFirestore db;
    private Spinner source;
    private EditText amount, date, note;
    private Button savebtn;
    private FirebaseAuth mAuth;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        mAuth=FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Spinner spinner = findViewById(sourcespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sourcesspinner, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        amount = (EditText) findViewById(R.id.amount);
        date = (EditText) findViewById(R.id.date);
        source = (Spinner) findViewById(sourcespinner);
        note = (EditText) findViewById(R.id.note);
        savebtn=(Button) findViewById(R.id.save);


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String incomeamount = amount.getText().toString();
                String incomedate = date.getText().toString();
                String incomesource = source.getSelectedItem().toString();
                String incomenote = note.getText().toString();
                userID = mAuth.getCurrentUser().getUid();

                Map<String, Object> data = new HashMap<>();
                data.put("Amount", incomeamount);
                data.put("Date", incomedate);
                data.put("Source", incomesource);
                data.put("Note", incomenote);


                db.collection("Users").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Income.this,"Income added", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String source = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), source, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
