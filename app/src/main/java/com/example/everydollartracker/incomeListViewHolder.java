package com.example.everydollartracker;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class incomeListViewHolder extends RecyclerView.ViewHolder {
    TextView amount, date, note, type, source;
    View view;

    incomeListViewHolder(View itemView)
    {
        super(itemView);
        amount = (TextView)itemView.findViewById(R.id.dashboardamount);
        date = (TextView)itemView.findViewById(R.id.dashboarddate);
        note = (TextView)itemView.findViewById(R.id.dashboardnote);
        type=(TextView)itemView.findViewById(R.id.dashboardtype);
        source=(TextView)itemView.findViewById(R.id.dahsboardsource);
        view  = itemView;
    }
}
