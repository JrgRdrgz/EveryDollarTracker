package com.example.everydollartracker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder>{
    Context context;
    ArrayList<InExStore> userlist;

    public adapter(Context context, ArrayList<InExStore> userlist) {
        this.context = context;
        this.userlist = userlist;
    }

    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView type, amount, source, date, note;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
