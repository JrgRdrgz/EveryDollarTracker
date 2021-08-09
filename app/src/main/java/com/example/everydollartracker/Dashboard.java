package com.example.everydollartracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dashboard extends Fragment{
    TextView incomeremaining;
    Calendar cal = Calendar.getInstance();
    RecyclerView rvItems;
    ItemsAdapter itemsAdapter;




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
        rvItems=(RecyclerView) view.findViewById(R.id.incomelist);
        incomeremaining= (TextView) view.findViewById(R.id.remainingincomeamount2);
        incomeremaining.setText(getRemaining());
        ////////////

        /////////////
        itemsAdapter = new ItemsAdapter();
        rvItems.setAdapter(itemsAdapter);

        rvItems.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Button addexpense=(Button) view.findViewById(R.id.addexpense);
        addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Expense.class);
                startActivity(intent);
            }
        });
        Button addincome=(Button) view.findViewById(R.id.addincome);
        addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Income.class);
                startActivity(intent);
            }
        });
        Button settings = (Button) view.findViewById(R.id.profile_id);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Settings.class);
                startActivity(intent);
            }
        });
        return view;

    }
    String getRemaining() {
        double income = 0.0;
        double expense = 0.0;
        Calendar cal = Calendar.getInstance();
        int thisMonth = cal.get(Calendar.MONTH)+1;
        int numDays =0;
        int thisDay = cal.get(Calendar.DAY_OF_MONTH);
        String result = "";


        if(thisMonth==1){
            numDays=31;
        }else if(thisMonth==2){
            numDays=28;
        }else if(thisMonth==3){
            numDays=31;
        }else if(thisMonth==4){
            numDays=30;
        }else if(thisMonth==5){
            numDays=31;
        }else if(thisMonth==6){
            numDays=30;
        }else if(thisMonth==7){
            numDays=31;
        }else if(thisMonth==8){
            numDays=31;
        }else if(thisMonth==9){
            numDays=30;
        }else if(thisMonth==10){
            numDays=31;
        }else if(thisMonth==11){
            numDays=30;
        }else if(thisMonth==12){
            numDays=31;
        }

        for (InExStore i : App_Page.inExArray){
            if(i.getType().equals("INCOME")){
                String dateString = i.getDate().substring(0,2); /*  08/07/2021 */
                int dateInt = Integer.parseInt(dateString);

                if(thisDay == dateInt){
                    income=income+ i.getAmount();
                }
            }
        }
        for (InExStore i : App_Page.inExArray){
            if(i.getType().equals("EXPENSES")){
                String dateString = i.getDate().substring(3,5); /*  08/07/2021 */
                int dateInt = Integer.parseInt(dateString);

                if(thisDay == dateInt){
                    expense=expense+ i.getAmount();
                }
            }
        }

        income=income/numDays;
        income = income-expense;
        result = String.format("%.2f",income);
        return result;
    }

}