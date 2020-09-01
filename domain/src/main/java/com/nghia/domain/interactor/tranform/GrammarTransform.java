package com.nghia.domain.interactor.tranform;

import java.util.ArrayList;
import java.util.List;

import com.nghia.data.local.entity.GrammarEntity;
import com.nghia.data.remote.dto.GrammarDto;
import com.nghia.domain.pojo.Grammar;

public class GrammarTransform {
    public static List<Grammar> toGrammarList(List<GrammarDto> dto) {
        List<Grammar> GrammarList = new ArrayList<>();
        if (dto != null) {
            for (GrammarDto GrammarDto : dto) {
                GrammarList.add(fromDto(GrammarDto));
            }
        }
        return GrammarList;
    }

    public static Grammar fromDto(GrammarDto dto) {
        Grammar Grammar = null;
        if (dto != null) {
            Grammar = new Grammar();
            Grammar.setLesson(dto.getLesson());
            Grammar.setContent(dto.getContent());
        }
        return Grammar;
    }

    public static Grammar fromEntity(GrammarEntity entity) {
        Grammar grammar = new Grammar();
        if (entity != null) {
            grammar.setLesson(entity.getLesson());
            grammar.setContent(entity.getContent());
        }
        return grammar;
    }

    public static GrammarEntity toEntity(Grammar grammar) {
        GrammarEntity entity = new GrammarEntity();
        if (grammar != null) {
            entity.setLesson(grammar.getLesson());
            entity.setContent(grammar.getContent());
        }
        return entity;
    }
}
