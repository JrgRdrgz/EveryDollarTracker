package com.example.everydollartracker;

import java.util.ArrayList;

public class User {
    public String name, email;

    public static class Income {
        String dateIn, noteIn, sourceIn;
        double amountIn;
        public Income(double amount, String date,String source, String note){
            this.dateIn = date;
            this.noteIn = note;
            this.amountIn = amount;
            this.sourceIn = source;

        }
    }
    static ArrayList<Income> incomes = new ArrayList<Income>();

    public static void addExpenses(double amount, String date,String source, String note) {
        Income newIn= new Income(amount,date,source,note);
        incomes.add(newIn);
    }


    public static class Expenses {
        String dateEx, noteEx, sourceEx;
        double amountEx;
        public Expenses(double amount, String date,String source, String note){
            this.dateEx = date;
            this.noteEx = note;
            this.amountEx = amount;
            this.sourceEx = source;

        }
    }
    static ArrayList<Expenses> Expensess = new ArrayList<Expenses>();

    public static void addIncome(double amount, String date,String source, String note) {
        Expenses newEx= new Expenses(amount,date,source,note);
        Expensess.add(newEx);
    }

    public User(){

    }

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

}
