package com.example.everydollartracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class notes_db extends SQLiteOpenHelper {

    private static final int db_version = 2;
    private static final String db_name = "notesDb";
    private static final String db_table = "notesTbl";

    // columns
    private static final String key_ID = "id";
    private static final String key_TITLE = "title";
    private static final String key_CONTEXT = "context";
    private static final String key_DATE = "date";


    notes_db (Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table
        String query = "CREATE TABLE "+ db_table + "(" +key_ID+" INT PRIMARY KEY,"+
                key_TITLE + " TEXT, "+
                key_CONTEXT + " TEXT, "+
                key_DATE + " TEXT"+ ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        if(oldV >= newV)
            return;
        db.execSQL("DROP TABLE IF EXISTS "+ db_table);
        onCreate(db);
    }
}
