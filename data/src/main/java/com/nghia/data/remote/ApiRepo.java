package com.nghia.data.remote;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import com.nghia.data.remote.dto.GrammarDto;
import com.nghia.data.remote.dto.KanjiDto;
import com.nghia.data.remote.dto.WordDto;

public interface ApiRepo {
    Single<List<KanjiDto>> getKanjiList();

    Single<List<WordDto>> getWordList();

    Single<List<GrammarDto>> getGrammarList();
}
