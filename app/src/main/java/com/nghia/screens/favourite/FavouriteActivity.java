package com.nghia.screens.favourite;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import com.nghia.domain.DomainComponent;
import com.nghia.domain.interactor.WordInteractor;
import com.nghia.domain.pojo.Word;
import com.nghia.R;

import java.util.Locale;
import java.util.UUID;

public class FavouriteActivity extends AppCompatActivity implements WordAdapter.WordItemViewHolder.OnWordClickListener {

    ImageView back;
    private WordInteractor wordInteractor = DomainComponent.getInstance().getWordInteractor(this);
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    View frameLayout;
    RecyclerView recyclerView;
    WordAdapter wordAdapter;
    TextToSpeech tts;
    TextView tvFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        setupView();
    }

    private void setupView() {
        back = findViewById(R.id.favourite_back);
        frameLayout = findViewById(R.id.fr_favourite);
        recyclerView = findViewById(R.id.rv_favourite);
        tvFavorite = findViewById(R.id.tv_favorite);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.JAPAN);
                    tts.setSpeechRate(1.0f);
                }
                else if (status == TextToSpeech.LANG_MISSING_DATA)
                    Toast.makeText(FavouriteActivity.this, "Missing language data", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(FavouriteActivity.this, "Text To Speech error", Toast.LENGTH_SHORT).show();
            }
        });


        frameLayout.setVisibility(View.VISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        getFavoriteWords();
    }

    private void updateWordFavorite(Word word, Action onComplete, Consumer<Throwable> throwable) {
        Disposable disposable = wordInteractor.updateFavoriteWord(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onComplete, throwable);
        compositeDisposable.add(disposable);
    }

    private void getFavoriteWords() {
        Disposable disposable = wordInteractor.getFavoriteWords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(words -> {
                    if(words.size()!=0) tvFavorite.setVisibility(View.GONE);
                    wordAdapter = new WordAdapter(this, words);
                    wordAdapter.notifyDataSetChanged();
                    wordAdapter.setOnWordClickListener(this);

                    recyclerView.setAdapter(wordAdapter);
                    frameLayout.setVisibility(View.GONE);
                }, throwable -> {
                    frameLayout.setVisibility(View.GONE);
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @Override
    public void onFavoriteIconClick(int position) {
        Word word = wordAdapter.getWords().get(position);
        if (word.getIsFavorite()) {
            word.setIsFavorite(false);
        } else {
            word.setIsFavorite(true);
        }
        updateWordFavorite(word, () -> wordAdapter.notifyItemChanged(position), throwable -> {
            Toast.makeText(FavouriteActivity.this, "Update error!!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onSpeakerIconClick(int position) {
        String utteranceId = UUID.randomUUID().toString();
        String word = wordAdapter.getWords().get(position).getWord();
        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }
}