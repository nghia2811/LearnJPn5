package com.nghia.data.remote.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GrammarDto {

    @SerializedName("lesson")
    @Expose
    private Integer lesson;
    @SerializedName("content")
    @Expose
    private String content;

    public Integer getLesson() {
        return lesson;
    }

    public void setLesson(Integer lesson) {
        this.lesson = lesson;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
