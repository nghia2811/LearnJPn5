package com.nghia.screens.alphabet;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.nghia.R;
import com.nghia.screens.kanji.KanjiActivity;

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.AlphabetItemViewHolder> {

    List<String> mData;
    Context mContext;
    TextToSpeech tts;

    public AlphabetAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
        tts = new TextToSpeech(mContext.getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.JAPAN);
                    tts.setSpeechRate(0.7f);
                } else if (status == TextToSpeech.LANG_MISSING_DATA)
                    Toast.makeText(mContext, "Missing language data", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(mContext, "Text To Speech error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public AlphabetItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hiragana, parent, false);
        return new AlphabetItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlphabetItemViewHolder holder, int position) {
        holder.vTitle.setText(mData.get(position));
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String utteranceId = UUID.randomUUID().toString();
                tts.speak(mData.get(position), TextToSpeech.QUEUE_FLUSH, null, utteranceId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    static class AlphabetItemViewHolder extends RecyclerView.ViewHolder {

        TextView vTitle;
        CardView itemLayout;

        public AlphabetItemViewHolder(@NonNull View itemView) {
            super(itemView);
            vTitle = itemView.findViewById(R.id.tv_word);
            itemLayout = itemView.findViewById(R.id.layout_word);
        }

    }
}
