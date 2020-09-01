package com.nghia.data.remote;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import com.nghia.data.remote.dto.GrammarDto;
import com.nghia.data.remote.dto.KanjiDto;
import com.nghia.data.remote.dto.WordDto;
import retrofit2.http.GET;

public interface Api {
    @GET("/kanji")
    Single<List<KanjiDto>> getKanjiList();

    @GET("/minaword")
    Single<List<WordDto>> getWordList();

    @GET("/grammar")
    Single<List<GrammarDto>> getGrammarList();
}
