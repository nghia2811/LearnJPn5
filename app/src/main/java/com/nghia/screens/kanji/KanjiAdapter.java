package com.nghia.screens.kanji;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nghia.R;
import com.nghia.domain.pojo.Kanji;
import com.nghia.domain.pojo.Word;
import com.nghia.screens.favourite.WordAdapter;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.List;

public class KanjiAdapter extends RecyclerView.Adapter<KanjiAdapter.KanjiItemViewHolder> {

    private List<Kanji> mData;
    Context mContext;
    private KanjiItemViewHolder.OnKanjiClickListener onKanjiClickListener;

    public KanjiAdapter(List<Kanji> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public KanjiItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kanji, parent, false);
        KanjiAdapter.KanjiItemViewHolder holder = new KanjiAdapter.KanjiItemViewHolder(view);
        holder.setOnKanjiClickListener(onKanjiClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull KanjiItemViewHolder holder, int position) {
        holder.vTitle.setText(mData.get(position).getKanji());
        holder.hira.setText(mData.get(position).getHira());
        holder.vn.setText(mData.get(position).getVn());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.easyFlipView.flipTheView();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<Kanji> getkanjis() {
        return mData;
    }

    public void setOnKanjiClickListener(KanjiAdapter.KanjiItemViewHolder.OnKanjiClickListener onKanjiClickListener) {
        this.onKanjiClickListener = onKanjiClickListener;
    }

    public static class KanjiItemViewHolder extends RecyclerView.ViewHolder {

        TextView vTitle, hira, vn;
        ImageView vPronounce;
        CardView itemLayout;
        EasyFlipView easyFlipView;
        OnKanjiClickListener onKanjiClickListener;

        public KanjiItemViewHolder(@NonNull View itemView) {
            super(itemView);
            vTitle = itemView.findViewById(R.id.kanji_title);
            hira = itemView.findViewById(R.id.kanji_hira);
            vn = itemView.findViewById(R.id.kanji_vn);
            vPronounce = itemView.findViewById(R.id.kanji_pronounce);
            itemLayout = itemView.findViewById(R.id.layout_kanji);
            easyFlipView = itemView.findViewById(R.id.flipView);

            vPronounce.setOnClickListener(view -> onKanjiClickListener.onSpeakerIconClick(getAdapterPosition()));
        }

        public void setOnKanjiClickListener(OnKanjiClickListener onKanjiClickListener) {
            this.onKanjiClickListener = onKanjiClickListener;
        }

        public interface OnKanjiClickListener {
            void onSpeakerIconClick(int position);
        }
    }
}
