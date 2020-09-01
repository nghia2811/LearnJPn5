package com.nghia.screens.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nghia.screens.alphabet.AlphabetActivity;
import com.nghia.screens.favourite.FavouriteActivity;

import java.util.List;

import com.nghia.R;
import com.nghia.screens.mina.MinaActivity;
import com.nghia.screens.quiz.QuizActivity;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryItemViewHolder> {
    Intent intent;
    List<Category> mData;
    Context mContext;

    public CategoryAdapter(Context mContext, List<Category> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {
        holder.vTitle.setText(mData.get(position).getTitle());
        holder.vImage.setImageResource(mData.get(position).getThumbnail());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mData.get(position).getTitle()) {
                    case "Bảng chữ cái":
                        intent = new Intent(mContext, AlphabetActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case "25 Bài Mina N5":
                        intent = new Intent(mContext, MinaActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case "Quiz":
                        intent = new Intent(mContext, QuizActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case "Từ vựng yêu thích":
                        intent = new Intent(mContext, FavouriteActivity.class);
                        mContext.startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        TextView vTitle;
        ImageView vImage;
        CardView itemLayout;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            vTitle = itemView.findViewById(R.id.cate_title);
            vImage = itemView.findViewById(R.id.cate_image);
            itemLayout = itemView.findViewById(R.id.layout_category);
        }

    }
}
