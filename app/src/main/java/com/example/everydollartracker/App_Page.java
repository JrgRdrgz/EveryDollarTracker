package com.example.everydollartracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class App_Page extends AppCompatActivity {
    static ArrayList<InExStore> inExArray = new ArrayList<InExStore>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
    public static void addInOrEx(double amount,String type, String date,String source, String note) {
        InExStore newInEx = new InExStore(amount, type, date, source, note);
        inExArray.add(newInEx);
    }
}