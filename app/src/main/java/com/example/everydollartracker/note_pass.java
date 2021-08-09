package com.example.everydollartracker;

import kotlin.text.UStringsKt;

public class note_pass {
    private long id;
    private String title, context, date;
    // two constructors

    note_pass(String title, String context, String date){
        this.title = title;
        this.context = context;
        this.date = date;
    }
    note_pass(long id, String title, String context, String date){
        this.id = id;
        this.title = title;
        this.context = context;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
