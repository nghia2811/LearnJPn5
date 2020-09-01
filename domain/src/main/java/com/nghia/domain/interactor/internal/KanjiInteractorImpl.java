package com.nghia.domain.interactor.internal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import com.nghia.data.DataComponent;
import com.nghia.data.local.LocalRepo;
import com.nghia.data.local.entity.KanjiEntity;
import com.nghia.data.remote.ApiRepo;
import com.nghia.data.remote.dto.KanjiDto;
import com.nghia.domain.interactor.KanjiInteractor;
import com.nghia.domain.interactor.tranform.KanjiTransform;
import com.nghia.domain.pojo.Kanji;

public class KanjiInteractorImpl implements KanjiInteractor {

    private LocalRepo localRepo;
    private ApiRepo apiRepo;

    public KanjiInteractorImpl(Context context) {
        localRepo = DataComponent.getInstance().getLocalRepo(context);
        apiRepo = DataComponent.getInstance().apiRepo;
    }

    @Override
    public Single<List<Kanji>> getKanjisFromLocal() {
        return localRepo.getKajis().map(entities -> {
            List<Kanji> kanjis = new ArrayList<>();
            for (KanjiEntity entity : entities)
                kanjis.add(KanjiTransform.fromEntity(entity));
            return kanjis;
        });
    }

    @Override
    public Completable insertKanjis(List<Kanji> kanjis) {
        List<KanjiEntity> entities = new ArrayList<>();
        for (Kanji kanji : kanjis)
            entities.add(KanjiTransform.toEntity(kanji));
        return localRepo.insertKanjis(entities);
    }

    @Override
    public Single<List<Kanji>> getKanjiList() {
        return apiRepo.getKanjiList().map(new Function<List<KanjiDto>, List<Kanji>>() {
            @Override
            public List<Kanji> apply(List<KanjiDto> kanjiDtos) throws Exception {
                return KanjiTransform.toKanjiList(kanjiDtos);
            }
        });
    }
}
