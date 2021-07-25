package com.example.everydollartracker;

public class InExStore {
    //User.addInEx(amount,type,date,source, note);
    private String type,date,source, note;
    private double amount;
    public InExStore() {    }
    public InExStore(double amount, String type, String date, String source,String note) {
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.source = source;
        this.note = note;
    }
    //
    public double getAmount() {
        return amount;
    }
    public String getType() {
        return type;
    }
    public String getDate() {
        return date;
    }
    public String getSource() {
        return source;
    }
    public String getTote() {
        return note;
    }
    //
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
