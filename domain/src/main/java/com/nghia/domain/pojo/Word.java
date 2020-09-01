package com.nghia.domain.pojo;

public class Word {
    private int id;
    private Boolean isFavorite;
    private String lesson;
    private String word;
    private String wordRomanji;
    private String wordVN;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
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

    public String getWordVN() {
        return wordVN;
    }

    public void setWordVN(String wordVN) {
        this.wordVN = wordVN;
    }

}