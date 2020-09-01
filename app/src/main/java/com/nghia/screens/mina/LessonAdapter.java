package com.nghia.screens.mina;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.nghia.R;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonItemViewHolder> {

    List<Integer> mData;
    Context mContext;

    public LessonAdapter(Context mContext, List<Integer> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public LessonItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
        return new LessonItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonItemViewHolder holder, int position) {
        holder.vTitle.setText("Lesson " + mData.get(position));
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LessonActivity.class);
                intent.putExtra("lesson", mData.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class LessonItemViewHolder extends RecyclerView.ViewHolder {

        TextView vTitle;
        CardView itemLayout;

        public LessonItemViewHolder(@NonNull View itemView) {
            super(itemView);
            vTitle = itemView.findViewById(R.id.tv_lesson);
            itemLayout = itemView.findViewById(R.id.layout_lesson);
        }

    }
}
