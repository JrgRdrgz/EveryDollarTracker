package com.example.everydollartracker;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dashboard extends Fragment {
    private DatabaseReference incomeref;
    private FirebaseAuth mAuth;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Dashboard() {
        // Required empty public constructor
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
    public static Dashboard newInstance(String param1, String param2) {
        Dashboard fragment = new Dashboard();
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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mAuth = FirebaseAuth.getInstance();
        incomeref= FirebaseDatabase.getInstance().getReference().child("dailyIncome").child(mAuth.getCurrentUser().getUid());

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

       /* Button addexpense=(Button) view.findViewById(R.id.addexpense);
        addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Expense.class);
                startActivity(intent);
            }
        });*/

        Button settings = (Button) view.findViewById(R.id.profile_id);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), Settings.class);
                startActivity(intent);
            }
        });
        return view;

    }

    private void addItem() {
        AlertDialog.Builder myIncome = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.activity_income,null);
        myIncome.setView(view);
        final AlertDialog dialog = myIncome.create();
        dialog.setCancelable(false);

        final Spinner categorySpinner = view.findViewById(R.id.spinner);
        final EditText amount = view.findViewById(R.id.incomeAmount);
        final Button cancel = view.findViewById(R.id.cancel);
        final Button add = view.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String incomeAmount = amount.getText().toString();
                String incomeCategory = categorySpinner.getSelectedItem().toString();

                if(TextUtils.isEmpty(incomeAmount)){
                    amount.setError("Please Enter Income Amount");
                    return;
                }
                if(incomeCategory.equals("Select Income Category")){
                    Toast.makeText(getActivity(),"Select Category", Toast.LENGTH_SHORT).show();
                }else{
                    String id = incomeref.push().getKey();
                    DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
                    Calendar calen= Calendar.getInstance();
                    String date=dateFormat.format(calen.getTime());

                    Data data = new Data(incomeCategory,date,id,8,Double.parseDouble(incomeAmount));
                    incomeref.child(id).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>(){
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(),"Income added", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(),"Failed to add income", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                dialog.dismiss();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}