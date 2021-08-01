package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class App_Page extends AppCompatActivity {
    public static   User thisUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    static ArrayList<InExStore> inExArray = new ArrayList<InExStore>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        addInOrEx((double)61,"INCOME", "07/28/2021","SALARY", "full time");
        addInOrEx((double)32,"INCOME", "07/28/2021","BONUS", "full time");
        addInOrEx((double)79,"EXPENSES", "07/25/2021","NEEDS", "PART time");
        addInOrEx((double)32,"INCOME", "07/28/2021","BONUS", "full time");
        addInOrEx((double)59,"EXPENSES", "07/25/2021","NEEDS", "PART time");
        addInOrEx((double)61,"INCOME", "07/28/2021","SALARY", "full time");
        addInOrEx((double)32,"INCOME", "07/28/2021","BONUS", "full time");
        addInOrEx((double)79,"EXPENSES", "07/25/2021","NEEDS", "PART time");
        addInOrEx((double)32,"INCOME", "07/28/2021","ALLOWANCE", "full time");
        addInOrEx((double)59,"EXPENSES", "07/25/2021","SAVINGS", "PART time");
        addInOrEx((double)50,"EXPENSES", "07/25/2021","NEEDS", "PART time");
        addInOrEx((double)100,"EXPENSES", "07/25/2021","WANTS", "PART time");
    }
    public static void addInOrEx(double amount,String type, String date,String source, String note) {
        InExStore newInEx = new InExStore(amount, type, date, source, note);
        inExArray.add(newInEx);
    }
    private void createNewUser() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        User newUser = new User(firebaseUser.getDisplayName(), firebaseUser.getUid());

        db.collection("users").document(firebaseUser.getUid()).set(newUser);

    }

}