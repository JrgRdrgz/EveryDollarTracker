package com.example.everydollartracker;

import android.widget.Spinner;

import java.text.DateFormat;
import java.util.Date;

public class Data {
    String category, date, id;
    int month;
    double amount;

    public Data() {
    }

    public Data(String category, String date, String id, int month, double amount) {
        this.category = category;
        this.date = date;
        this.id = id;
        this.month = month;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
