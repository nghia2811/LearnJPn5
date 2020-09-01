package com.nghia.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "words")
public class WordEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String wordVN;

    private String word;

    private String wordRomanji;

    private boolean isFavorite;

    private String lesson;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getWordVN() {
        return wordVN;
    }

    public void setWordVN(String wordVN) {
        this.wordVN = wordVN;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordRomanji() {
        return wordRomanji;
    }

    public void setWordRomanji(String wordRomanji) {
        this.wordRomanji = wordRomanji;
    }
}
