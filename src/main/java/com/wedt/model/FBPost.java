package com.wedt.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Set;

public class FBPost {
    private String id;
    @SerializedName("message")
    private String msg;
    @SerializedName("updated_time")
    private LocalDateTime date;
    private Set<String> keywords;

    public FBPost(String id, String msg, LocalDateTime date, Set<String> keywords) {
        this.id = id;
        this.msg = msg;
        this.date = date;
        this.keywords = keywords;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "FBPost{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
