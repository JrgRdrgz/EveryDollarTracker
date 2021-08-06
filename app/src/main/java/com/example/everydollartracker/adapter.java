package com.example.everydollartracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class adapter extends RecyclerView.Adapter<incomeListViewHolder>{
    List<InExStore> list = Collections.emptyList();
    Context context;
    ClickListener listener;

    public adapter(List<InExStore> list, Context context, ClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }



    @NonNull
    @Override
    public incomeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.incomelist,
                        parent, false);

        incomeListViewHolder viewHolder = new incomeListViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final incomeListViewHolder viewHolder,
                     final int position)
    {
        /*final index = viewHolder.getAdapterPosition();*/
        viewHolder.amount.setText(String.valueOf(list.get(position).amount));
        viewHolder.date.setText(list.get(position).date);
        viewHolder.note.setText(list.get(position).note);
        viewHolder.source.setText(list.get(position).source);
        viewHolder.type.setText(list.get(position).type);

        /*viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                listiner.click(index);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
