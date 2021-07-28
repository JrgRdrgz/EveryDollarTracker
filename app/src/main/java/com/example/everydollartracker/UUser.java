package com.example.everydollartracker;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UUser
{
    private DatabaseReference DBR;
    public UUser()
    {
        FirebaseDatabase FB = FirebaseDatabase.getInstance();
        DBR = FB.getReference(User.class.getSimpleName());
    }

    public Task<Void> add(profile_info user1)
    {

       return DBR.push().setValue(user1);
    }



}
