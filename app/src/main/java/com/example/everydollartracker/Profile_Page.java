package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Profile_Page extends AppCompatActivity
{

    private ImageView profile_image;
    private Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    //EditText Email, Password, DOF, FullName, UserName, Income, Expenses;
    DatabaseReference DBR;
    //Button BUpdate;
    Intent myIntent = new Intent(this, Dashboard.class);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);


        final ImageView profile_image = (ImageView) findViewById(R.id.profile_image);
        final EditText Email = (EditText) findViewById(R.id.email_id);
        final EditText Password = (EditText) findViewById(R.id.password_id);
        final EditText DOF = (EditText) findViewById(R.id.dateofbirth_id);
        final EditText FullName = (EditText) findViewById(R.id.Fullname);
        final EditText UserName = (EditText) findViewById(R.id.Username);
        final EditText Income = (EditText) findViewById(R.id.Income_M);
        final EditText Expenses = (EditText) findViewById(R.id.Expense_M);
        Button BUpdate = (Button) findViewById(R.id.upp);
        UUser user1 = new UUser();

        String emailVal = Email.getText().toString().trim();
        String passwordVal = Password.getText().toString().trim();
        String DOFVal = DOF.getText().toString();
        String FullNameVal = FullName.getText().toString().trim();
        String UserNameVal = UserName.getText().toString().trim();
        String IncomeVal = Income.getText().toString().trim();
        String ExpensesVal = Expenses.getText().toString().trim();


        DBR = FirebaseDatabase.getInstance().getReference().child("Existed User");

        BUpdate.setOnClickListener(v ->
        {
            if (TextUtils.isEmpty(emailVal))
            {
                Email.setError("Enter an Email");
                return;
            }
            if (TextUtils.isEmpty(passwordVal))
            {
                Password.setError("Enter Password");
                return;
            }
            if (TextUtils.isEmpty(DOFVal))
            {
                DOF.setError("Enter your Date of Birth");
                return;
            }
            if (TextUtils.isEmpty(FullNameVal))
            {
                FullName.setError("Enter your Full Name");
                return;
            }
            if (TextUtils.isEmpty(UserNameVal))
            {
                UserName.setError("Enter User Name");
                return;
            }
            if (TextUtils.isEmpty(IncomeVal))
            {
                Income.setError("Enter Total Income");
                return;
            }
            if (TextUtils.isEmpty(ExpensesVal))
            {
                Expenses.setError("Enter Total Expenses");
                return;
            }

            DBR.push().setValue(emailVal);
            DBR.push().setValue(passwordVal);
            DBR.push().setValue(DOFVal);
            DBR.push().setValue(FullNameVal);
            DBR.push().setValue(UserNameVal);
            DBR.push().setValue(IncomeVal);
            DBR.push().setValue(ExpensesVal);


            profile_info pro = new profile_info(Email.getText().toString().trim(), Password.getText().toString().trim(), DOF.getText().toString(), FullName.getText().toString().trim(), UserName.getText().toString().trim(), Income.getText().toString().trim(), Expenses.getText().toString().trim());
            user1.add(pro).addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->
            {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }


                //Toast.makeText(Dashboard.this, "Profile Updated!" , Toast.LENGTH_SHORT).show();
}