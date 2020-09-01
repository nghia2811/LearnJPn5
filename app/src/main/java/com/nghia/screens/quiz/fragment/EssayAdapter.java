package com.nghia.screens.quiz.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.nghia.R;

public class EssayAdapter extends RecyclerView.Adapter<EssayAdapter.AlphabetItemViewHolder> {

    List<String> mData;
    EssayFragment mContext;

    public EssayAdapter(EssayFragment mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public AlphabetItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word_essay, parent, false);
        return new AlphabetItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlphabetItemViewHolder holder, int position) {
        holder.vTitle.setText(mData.get(position));
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String before = String.valueOf(mContext.answer.getText());
                StringBuilder sb = new StringBuilder(before);
                sb.append(mData.get(position));
                mContext.answer.setText(sb);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class AlphabetItemViewHolder extends RecyclerView.ViewHolder {

        TextView vTitle;
        CardView itemLayout;

        public AlphabetItemViewHolder(@NonNull View itemView) {
            super(itemView);
            vTitle = itemView.findViewById(R.id.tv_word_essay);
            itemLayout = itemView.findViewById(R.id.layout_word_essay);
        }

    }
}
