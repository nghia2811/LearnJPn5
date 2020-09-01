package com.nghia.screens.favourite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nghia.R;
import com.nghia.domain.pojo.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordItemViewHolder> {

    List<Word> mData;
    Context mContext;
    private WordItemViewHolder.OnWordClickListener onWordClickListener;

    public WordAdapter(Context mContext, List<Word> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public WordItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        WordItemViewHolder holder = new WordItemViewHolder(view);
        holder.setOnWordClickListener(onWordClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WordItemViewHolder holder, int position) {
        holder.word.setText(mData.get(position).getWord());
        holder.romanji.setText(mData.get(position).getWordRomanji());
        holder.vn.setText(mData.get(position).getWordVN());
        if (mData.get(position).getIsFavorite())
            holder.isFavourite.setImageResource(R.drawable.ic_baseline_favorite_24);
        else holder.isFavourite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<Word> getWords() {
        return mData;
    }

    public void setOnWordClickListener(WordItemViewHolder.OnWordClickListener onWordClickListener) {
        this.onWordClickListener = onWordClickListener;
    }

    public static class WordItemViewHolder extends RecyclerView.ViewHolder {

        TextView word, romanji, vn;
        ImageView isFavourite, speaker;
        OnWordClickListener onWordClickListener;

        public WordItemViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.tv_name);
            romanji = itemView.findViewById(R.id.tv_romanji);
            vn = itemView.findViewById(R.id.tv_viet);
            isFavourite = itemView.findViewById(R.id.iv_isFavourite);
            speaker = itemView.findViewById(R.id.iv_speaker);

            isFavourite.setOnClickListener(view -> onWordClickListener.onFavoriteIconClick(getAdapterPosition()));
            speaker.setOnClickListener(view -> onWordClickListener.onSpeakerIconClick(getAdapterPosition()));
        }

        public void setOnWordClickListener(OnWordClickListener onWordClickListener) {
            this.onWordClickListener = onWordClickListener;
        }

        public interface OnWordClickListener {
            void onFavoriteIconClick(int position);

            void onSpeakerIconClick(int position);

        }
    }
}
