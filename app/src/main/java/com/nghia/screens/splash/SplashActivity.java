package com.nghia.screens.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nghia.R;
import com.nghia.domain.DomainComponent;
import com.nghia.domain.interactor.GrammarInteractor;
import com.nghia.domain.interactor.KanjiInteractor;
import com.nghia.domain.interactor.WordInteractor;
import com.nghia.domain.pojo.Grammar;
import com.nghia.domain.pojo.Kanji;
import com.nghia.domain.pojo.Word;
import com.nghia.screens.home.HomeActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    private WordInteractor wordInteractor;
    private GrammarInteractor grammarInteractor;
    private KanjiInteractor kanjiInteractor;
    View view;
    Handler handler;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        view = findViewById(R.id.splash_layout);

        wordInteractor = DomainComponent.getInstance().getWordInteractor(SplashActivity.this);
        grammarInteractor = DomainComponent.getInstance().getGrammarInteractor(SplashActivity.this);
        kanjiInteractor = DomainComponent.getInstance().getKanjiInteractor(SplashActivity.this);

        gotoMain();

//        Disposable disposable = Observable.merge(
//                getWordsFromLocal().toObservable(),
//                getGrammarFromLocal().toObservable(),
//                getKanjisFromLocal().toObservable()
//        )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Object>() {
//                               @Override
//                               public void accept(Object s) throws Exception {
//                               }
//                           }
//                        ,
//                        new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                            }
//                        },
//                        new Action() {
//                            @Override
//                            public void run() throws Exception {
//                                gotoMain();
//                            }
//                        });
//        compositeDisposable.add(disposable);
    }

    public void gotoMain() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        }, 2000);
    }

    private Single<List<Word>> getWordsFromLocal() {
        return wordInteractor.getWordsFromLocal()
                .flatMap((Function<List<Word>, SingleSource<List<Word>>>) words -> {
                    if (words.isEmpty()) {
                        return getWordsFromRemote();
                    } else {
                        return Single.just(words);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<List<Word>> getWordsFromRemote() {
        return wordInteractor.getWordList().flatMap(
                (words) -> wordInteractor.insertWords(words).toSingleDefault(words)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
        );
    }

    private Single<List<Grammar>> getGrammarFromLocal() {
        return grammarInteractor.getGrammarFromLocal()
                .flatMap((Function<List<Grammar>, SingleSource<List<Grammar>>>) grammars -> {
                    if (grammars.isEmpty()) {
                        return getGrammarFromRemote();
                    } else {
                        return Single.just(grammars);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<List<Grammar>> getGrammarFromRemote() {
        return grammarInteractor.getGrammarList().flatMap(
                (grammars) -> grammarInteractor.insertGrammars(grammars).toSingleDefault(grammars)
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    private Single<List<Kanji>> getKanjisFromLocal() {
        return kanjiInteractor.getKanjisFromLocal()
                .flatMap((Function<List<Kanji>, SingleSource<List<Kanji>>>) kanjis -> {
                    if (kanjis.isEmpty()) {
                        return getKanjisFromRemote();
                    } else {
                        return Single.just(kanjis);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<List<Kanji>> getKanjisFromRemote() {
        return kanjiInteractor.getKanjiList().flatMap(
                (kanjis) -> kanjiInteractor.insertKanjis(kanjis).toSingleDefault(kanjis)
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
        handler.removeCallbacksAndMessages(null);
    }
}
