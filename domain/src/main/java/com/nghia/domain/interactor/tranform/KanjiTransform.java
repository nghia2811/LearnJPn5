package com.nghia.domain.interactor.tranform;

import java.util.ArrayList;
import java.util.List;

import com.nghia.data.local.entity.KanjiEntity;
import com.nghia.data.remote.dto.KanjiDto;
import com.nghia.domain.pojo.Kanji;

public class KanjiTransform {
    public static List<Kanji> toKanjiList(List<KanjiDto> dto) {
        List<Kanji> kanjiList = new ArrayList<>();
        if (dto != null) {
            for (KanjiDto kanjiDto : dto) {
                kanjiList.add(fromDto(kanjiDto));
            }
        }
        return kanjiList;
    }

    public static Kanji fromDto(KanjiDto dto) {
        Kanji kanji = null;
        if (dto != null) {
            kanji = new Kanji();
            kanji.setId(dto.getId());
            kanji.setHira(dto.getHira());
            kanji.setKanji(dto.getKanji());
            kanji.setVn(dto.getVn());
        }
        return kanji;
    }

    public static Kanji fromEntity(KanjiEntity entity) {
        Kanji kanji = new Kanji();
        if (entity != null) {
            kanji.setId(entity.getId());
            kanji.setHira(entity.getHira());
            kanji.setKanji(entity.getKanji());
            kanji.setVn(entity.getVn());
        }
        return kanji;
    }

    public static KanjiEntity toEntity(Kanji kanji) {
        KanjiEntity entity = new KanjiEntity();
        if (kanji != null) {
            entity.setId(kanji.getId());
            entity.setHira(kanji.getHira());
            entity.setKanji(kanji.getKanji());
            entity.setVn(kanji.getVn());
        }
        return entity;
    }
}
