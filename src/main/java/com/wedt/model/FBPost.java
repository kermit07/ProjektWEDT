package com.wedt.model;

public class FBPost {
    private String id;
    private String message;

    public FBPost(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    public String toString() {
        return "FBPost{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
