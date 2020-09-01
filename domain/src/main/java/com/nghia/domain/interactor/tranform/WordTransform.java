package com.nghia.domain.interactor.tranform;

import com.nghia.data.local.entity.WordEntity;
import com.nghia.data.remote.dto.WordDto;
import com.nghia.domain.pojo.Word;

public class WordTransform {

    public static Word fromDto(WordDto dto) {
        Word word = new Word();
        if (dto != null) {
            word.setIsFavorite(dto.getIsFavorite());
            word.setWord(dto.getWord());
            word.setWordRomanji(dto.getWordRomanji());
            word.setWordVN(dto.getWordVN());
            word.setLesson(dto.getLesson());
        }
        return word;
    }


    public static Word fromEntity(WordEntity entity) {
        Word word = new Word();
        if (entity != null) {
            word.setId(entity.getId());
            word.setIsFavorite(entity.getIsFavorite());
            word.setWord(entity.getWord());
            word.setWordRomanji(entity.getWordRomanji());
            word.setWordVN(entity.getWordVN());
            word.setLesson(entity.getLesson());
        }
        return word;
    }

    public static WordEntity toEntity(Word word) {
        WordEntity entity = new WordEntity();
        if (word != null) {
            entity.setId(word.getId());
            entity.setIsFavorite(word.getIsFavorite());
            entity.setWord(word.getWord());
            entity.setWordRomanji(word.getWordRomanji());
            entity.setWordVN(word.getWordVN());
            entity.setLesson(word.getLesson());
        }
        return entity;
    }

}
