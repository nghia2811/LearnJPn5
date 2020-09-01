package com.nghia.data.local;

import android.content.Context;

import com.nghia.data.local.database.AppRoomDatabase;
import com.nghia.data.local.entity.GrammarEntity;
import com.nghia.data.local.entity.KanjiEntity;
import com.nghia.data.local.entity.WordEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class LocalRepoImpl implements LocalRepo {

    private AppRoomDatabase database;

    public LocalRepoImpl(Context context) {
        database = AppRoomDatabase.getInstance(context);
    }

    @Override
    public Single<List<WordEntity>> getWords() {
        return database.wordDao().getWords();
    }

    @Override
    public Flowable<List<WordEntity>> getFavoriteWords() {
        return database.wordDao().getFavoriteWords();
    }

    @Override
    public Completable insertWords(List<WordEntity> wordEntities) {
        return database.wordDao().insertWords(wordEntities);
    }

    @Override
    public Completable updateFavoriteWord(WordEntity wordEntity) {
        return database.wordDao().updateFavoriteWord(wordEntity);
    }

    @Override
    public Single<List<GrammarEntity>> getGrammar() {
        return database.grammarDao().getGrammar();
    }

    @Override
    public Completable insertGrammars(List<GrammarEntity> entities) {
        return database.grammarDao().insertGrammars(entities);
    }

    @Override
    public Single<List<KanjiEntity>> getKajis() {
        return database.kanjiDao().getKanjis();
    }

    @Override
    public Completable insertKanjis(List<KanjiEntity> entities) {
        return database.kanjiDao().insertKanjis(entities);
    }
}
