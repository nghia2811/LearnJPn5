package com.nghia.data.remote.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WordDto {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("isFavorite")
    @Expose
    private Boolean isFavorite;
    @SerializedName("lesson")
    @Expose
    private String lesson;
    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("wordRomanji")
    @Expose
    private String wordRomanji;
    @SerializedName("wordKanji")
    @Expose
    private String wordKanji;
    @SerializedName("wordTQ")
    @Expose
    private String wordTQ;
    @SerializedName("wordVN")
    @Expose
    private String wordVN;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getWordKanji() {
        return wordKanji;
    }

    public void setWordKanji(String wordKanji) {
        this.wordKanji = wordKanji;
    }

    public String getWordTQ() {
        return wordTQ;
    }

    public void setWordTQ(String wordTQ) {
        this.wordTQ = wordTQ;
    }

    public String getWordVN() {
        return wordVN;
    }

    public void setWordVN(String wordVN) {
        this.wordVN = wordVN;
    }

}