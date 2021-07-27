package com.example.everydollartracker;

import java.util.ArrayList;

public class User {
    public String name, email;
    public User(){

    }

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }
    private ArrayList <InExStore> inExStoreArrayList = new ArrayList<InExStore>();
    public void addInOrEx(double amount,String type,String date,String source,String note) {
        InExStore newInEx = new InExStore(amount,type,date,source,note);
        inExStoreArrayList.add(newInEx);
    }

}
