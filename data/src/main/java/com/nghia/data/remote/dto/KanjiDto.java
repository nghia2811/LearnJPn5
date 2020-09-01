package com.nghia.data.remote.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KanjiDto {

    @SerializedName("hira")
    @Expose
    private String hira;
    @SerializedName("kanji")
    @Expose
    private String kanji;
    @SerializedName("vn")
    @Expose
    private String vn;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getHira() {
        return hira;
    }

    public void setHira(String hira) {
        this.hira = hira;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}


