package com.example.everydollartracker;

import android.content.Context;
import android.view.LayoutInflater;
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

        View v = LayoutInflater.from(context).inflate(R.layout.incomelist, parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {
        InExStore user = userlist.get(position);
        holder.amount.setText(String.valueOf(user.amount));
        holder.type.setText((user.type));
        holder.date.setText((user.date));
        holder.note.setText((user.note));
        holder.source.setText((user.source));



    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView type, amount, source, date, note;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            type = itemView.findViewById(R.id.dashboardtype);
            amount = itemView.findViewById(R.id.dashboardamount);
            source = itemView.findViewById(R.id.dahsboardsource);
            date = itemView.findViewById(R.id.dashboarddate);
            note = itemView.findViewById(R.id.dashboardnote);



        }
    }
}
