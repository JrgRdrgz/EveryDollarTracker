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
        incomeremaining= (TextView) view.findViewById(R.id.remainingincomeamount2);
        incomeremaining.setText(getRemaining());

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
        String textA="";
        double xa=0.0;
        for (InExStore i : App_Page.inExArray){
            if(i.getType().equals("INCOME")){
                xa=xa+ i.getAmount();
            }
        }
        textA=Double.toString(xa);
        return textA;
    }

    private String getRemainings() {
        String remainingamount ="";
        double amount = 0.0;
        double numDays = 0.0;
        int thisMonth=(cal.get(Calendar.MONTH))+1;
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

        //for (int i = 0; i < App_Page.inExArray.size(); i++)
        for (InExStore i : App_Page.inExArray){
            if(i.getType().equals("INCOME")){
                amount=amount+ i.getAmount();
            }
        }
        amount=amount / numDays;

        for (InExStore i : App_Page.inExArray){
            if(i.getType().equals("EXPENSE")){
                amount=amount - i.getAmount();
            }
        }

        remainingamount=Double.toString(amount);
        return remainingamount;
    }

    /*private void EventChange() {
        db.collection("users").document("nLhuxjHFPHcbORUj9U4jYbFqmH52").collection("inExArray")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            Log.e("Error Retrieving List", error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                userlist.add(dc.getDocument().toObject(InExStore.class));
                            }
                            myadapter.notifyDataSetChanged();
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                        }
                    }
                });
    }*/
}