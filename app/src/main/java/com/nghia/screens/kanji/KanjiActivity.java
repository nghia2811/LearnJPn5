package com.nghia.screens.kanji;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.nghia.R;
import com.nghia.domain.DomainComponent;
import com.nghia.domain.interactor.KanjiInteractor;
import com.nghia.domain.pojo.Kanji;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class KanjiActivity extends AppCompatActivity implements KanjiAdapter.KanjiItemViewHolder.OnKanjiClickListener {
    ImageView back;
    private KanjiInteractor kanjiInteractor = DomainComponent.getInstance().getKanjiInteractor(this);
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    View frameLayout;
    KanjiAdapter kanjiAdapter;
    TextToSpeech tts;
    RecyclerViewPager mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji);
        back = findViewById(R.id.kanji_back);
        mRecyclerView = findViewById(R.id.rv_kanji);
        frameLayout = findViewById(R.id.fr_kanji);

        setupView();
    }

    private void setupView() {
        frameLayout.setVisibility(View.VISIBLE);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.JAPAN);
                    tts.setSpeechRate(0.7f);
                } else if (status == TextToSpeech.LANG_MISSING_DATA)
                    Toast.makeText(KanjiActivity.this, "Missing language data", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(KanjiActivity.this, "Text To Speech error", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager layout = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layout);

        getKanjisFromLocal();
    }

    private void getKanjisFromLocal() {
        Disposable disposable = kanjiInteractor.getKanjisFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kanjis -> {
                    if(kanjis.size() == 0)
                        getKanjisFromRemote();
                    else{
                        kanjiAdapter = new KanjiAdapter(kanjis, KanjiActivity.this);
                        kanjiAdapter.setOnKanjiClickListener(this);

                        mRecyclerView.setAdapter(kanjiAdapter);
                        frameLayout.setVisibility(View.GONE);
                    }

                }, throwable -> {
                    frameLayout.setVisibility(View.GONE);
                });
        compositeDisposable.add(disposable);
    }

    private void getKanjisFromRemote() {
        Disposable disposable = kanjiInteractor.getKanjiList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kanjis -> {
                    saveKanjisToLocal(kanjis);
                }, throwable -> {
                });
        compositeDisposable.add(disposable);
    }

    private void saveKanjisToLocal(List<Kanji> kanjis) {
        Disposable disposable = kanjiInteractor.insertKanjis(kanjis)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        compositeDisposable.add(disposable);
    }
    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @Override
    public void onSpeakerIconClick(int position) {
        String utteranceId = UUID.randomUUID().toString();
        String word = kanjiAdapter.getkanjis().get(position).getKanji();
        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }
}