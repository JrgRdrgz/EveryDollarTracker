package com.example.everydollartracker;

import java.util.ArrayList;

public class User {
    public String name, email;

    public static class InEx {
        String date, note, source, type;
        double amount;
        public InEx(double amount,String type, String date,String source, String note){
            this.amount = amount;
            this.type = type;
            this.date = date;
            this.source = source;
            this.note = note;
        }
    }
    static ArrayList<InEx> inex = new ArrayList<InEx>();
    public static void addInEx(double amount,String type, String date,String source, String note) {
        InEx newInEx= new InEx(amount,type,date,source,note);
        inex.add(newInEx);
    }
    public User(){

    }

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

}
