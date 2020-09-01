package com.nghia.domain.interactor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import com.nghia.domain.pojo.Kanji;

public interface KanjiInteractor {

    Single<List<Kanji>> getKanjiList();

    Single<List<Kanji>> getKanjisFromLocal();

    Completable insertKanjis(List<Kanji> kanjis);
}
