package com.nghia.data.local;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import com.nghia.data.local.entity.GrammarEntity;
import com.nghia.data.local.entity.KanjiEntity;
import com.nghia.data.local.entity.WordEntity;

public interface LocalRepo {

    Single<List<WordEntity>> getWords();

    Flowable<List<WordEntity>> getFavoriteWords();

    Completable insertWords(List<WordEntity> entities);

    Completable updateFavoriteWord(WordEntity wordEntity);

    Single<List<GrammarEntity>> getGrammar();

    Completable insertGrammars(List<GrammarEntity> entities);

    Single<List<KanjiEntity>> getKajis();

    Completable insertKanjis(List<KanjiEntity> entities);
}
