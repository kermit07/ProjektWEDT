package com.wedt.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class FBPost {
    private String id;
    @SerializedName("message")
    private String msg;
    @SerializedName("updated_time")
    private LocalDateTime date;

    public FBPost(String id, String message, LocalDateTime date) {
        this.id = id;
        this.msg = message;
        this.date = date;
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

    @Override
    public String toString() {
        return "FBPost{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
