package com.nghia.screens.quiz.numberquizpicker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nghia.R;

import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberHolder> {

    @NonNull
    @Override
    public NumberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_number, parent, false);
        return new NumberHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberHolder holder, int position) {
        if (position > 1 && position < 52 )
            holder.setNumber(String.valueOf(position -1));
        else
            holder.setNumber("");
    }

    @Override
    public int getItemCount() {
        return 54;
    }

    static class NumberHolder extends RecyclerView.ViewHolder {

        TextView vNumber;

        public NumberHolder(@NonNull View itemView) {
            super(itemView);
            vNumber = itemView.findViewById(R.id.tv_number);
        }

        private void setNumber(String number) {
            vNumber.setText(number);
        }
    }
}
