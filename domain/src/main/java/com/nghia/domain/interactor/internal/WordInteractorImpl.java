package com.nghia.domain.interactor.internal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import com.nghia.data.DataComponent;
import com.nghia.data.local.LocalRepo;
import com.nghia.data.local.entity.WordEntity;
import com.nghia.data.remote.ApiRepo;
import com.nghia.data.remote.dto.WordDto;
import com.nghia.domain.interactor.WordInteractor;
import com.nghia.domain.interactor.tranform.WordTransform;
import com.nghia.domain.pojo.Word;

public class WordInteractorImpl implements WordInteractor {

    private LocalRepo localRepo;
    private ApiRepo apiRepo;

    public WordInteractorImpl(Context context) {
        localRepo = DataComponent.getInstance().getLocalRepo(context);
        apiRepo = DataComponent.getInstance().apiRepo;
    }

    @Override
    public Single<List<Word>> getWordsFromLocal() {
        return localRepo.getWords().map(wordEntities -> {
            List<Word> words = new ArrayList<>();
            for (WordEntity entity : wordEntities)
                words.add(WordTransform.fromEntity(entity));
            return words;
        });
    }

    @Override
    public Flowable<List<Word>> getFavoriteWords() {
        return localRepo.getFavoriteWords().map(wordEntities -> {
            List<Word> words = new ArrayList<>();
            for (WordEntity entity : wordEntities)
                words.add(WordTransform.fromEntity(entity));
            return words;
        });
    }

    @Override
    public Completable insertWords(List<Word> words) {
        List<WordEntity> entities = new ArrayList<>();
        for (Word word : words)
            entities.add(WordTransform.toEntity(word));
        return localRepo.insertWords(entities);
    }

    @Override
    public Completable updateFavoriteWord(Word word) {
        return localRepo.updateFavoriteWord(WordTransform.toEntity(word));
    }

    @Override
    public Single<List<Word>> getWordList() {
        return apiRepo.getWordList().map(wordDtos -> {
            List<Word> words = new ArrayList<>();
            for (WordDto dto : wordDtos)
                words.add(WordTransform.fromDto(dto));
            return words;
        });
    }
}
