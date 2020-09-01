package com.nghia.data.remote;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import com.nghia.data.remote.dto.GrammarDto;
import com.nghia.data.remote.dto.KanjiDto;
import com.nghia.data.remote.dto.WordDto;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRepoImpl implements ApiRepo {

    OkHttpClient client = new OkHttpClient();
    Api api;

    public ApiRepoImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo0339572.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    @Override
    public Single<List<KanjiDto>> getKanjiList() {
        return api.getKanjiList();
    }

    @Override
    public Single<List<WordDto>> getWordList() {
        return api.getWordList();
    }

    @Override
    public Single<List<GrammarDto>> getGrammarList() {
        return api.getGrammarList();
    }

}
