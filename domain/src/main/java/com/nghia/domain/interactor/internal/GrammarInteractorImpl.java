package com.nghia.domain.interactor.internal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import com.nghia.data.DataComponent;
import com.nghia.data.local.LocalRepo;
import com.nghia.data.local.entity.GrammarEntity;
import com.nghia.data.local.entity.KanjiEntity;
import com.nghia.data.remote.ApiRepo;
import com.nghia.data.remote.dto.GrammarDto;
import com.nghia.domain.interactor.GrammarInteractor;
import com.nghia.domain.interactor.tranform.GrammarTransform;
import com.nghia.domain.interactor.tranform.KanjiTransform;
import com.nghia.domain.pojo.Grammar;
import com.nghia.domain.pojo.Kanji;

public class GrammarInteractorImpl implements GrammarInteractor {

    private LocalRepo localRepo;
    private ApiRepo apiRepo;

    public GrammarInteractorImpl(Context context) {
        localRepo = DataComponent.getInstance().getLocalRepo(context);
        apiRepo = DataComponent.getInstance().apiRepo;
    }

    @Override
    public Single<List<Grammar>> getGrammarFromLocal() {
        return localRepo.getGrammar().map(entities -> {
            List<Grammar> grammars = new ArrayList<>();
            for (GrammarEntity entity : entities)
                grammars.add(GrammarTransform.fromEntity(entity));
            return grammars;
        });
    }

    @Override
    public Completable insertGrammars(List<Grammar> grammars) {
        List<GrammarEntity> entities = new ArrayList<>();
        for (Grammar grammar : grammars)
            entities.add(GrammarTransform.toEntity(grammar));
        return localRepo.insertGrammars(entities);
    }

    @Override
    public Single<List<Grammar>> getGrammarList() {
        return apiRepo.getGrammarList().map(new Function<List<GrammarDto>, List<Grammar>>() {
            @Override
            public List<Grammar> apply(List<GrammarDto> GrammarDtos) throws Exception {
                return GrammarTransform.toGrammarList(GrammarDtos);
            }
        });
    }
}
