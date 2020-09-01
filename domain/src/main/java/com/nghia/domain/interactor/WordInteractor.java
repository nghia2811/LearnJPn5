package com.nghia.domain.interactor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import com.nghia.domain.pojo.Word;

public interface WordInteractor {

    //remote
    Single<List<Word>> getWordList();

    //local
    Single<List<Word>> getWordsFromLocal();

    Flowable<List<Word>> getFavoriteWords();

    Completable insertWords(List<Word> words);

    Completable updateFavoriteWord(Word word);
}
