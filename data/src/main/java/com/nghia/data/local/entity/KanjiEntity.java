package com.nghia.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "kanji")
public class KanjiEntity {

    @PrimaryKey
    private int id;

    private String hira;

    private String kanji;

    private String vn;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
