package com.nghia.domain.interactor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import com.nghia.domain.pojo.Grammar;
import com.nghia.domain.pojo.Kanji;

public interface GrammarInteractor {

    Single<List<Grammar>> getGrammarList();

    Single<List<Grammar>> getGrammarFromLocal();

    Completable insertGrammars(List<Grammar> grammars);

}
