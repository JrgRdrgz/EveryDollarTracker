package com.example.everydollartracker;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_Page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_Page extends Fragment {

    DatabaseReference DBR;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile_Page() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile_Page newInstance(String param1, String param2) {
        Profile_Page fragment = new Profile_Page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_page, container, false);
        final ImageView profile_image = (ImageView) view.findViewById(R.id.profile_image);
        final EditText Email = (EditText) view.findViewById(R.id.email_id);
        final EditText Password = (EditText) view.findViewById(R.id.password_id);
        final EditText DOF = (EditText) view.findViewById(R.id.dateofbirth_id);
        final EditText FullName = (EditText) view.findViewById(R.id.Fullname);
        final EditText UserName = (EditText) view.findViewById(R.id.Username);
        final EditText Income = (EditText) view.findViewById(R.id.Income_M);
        final EditText Expenses = (EditText) view.findViewById(R.id.Expense_M);
        Button BUpdate = (Button) view.findViewById(R.id.upp);
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
                //Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->
            {
                //Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
        return view;

    }
}