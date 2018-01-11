package com.wedt.model;

import java.util.List;

public class FBPostResult {
    private FBPost post;
    private List<String> keywords;
    private double result;
    private FBPostKind kind;

    public FBPostResult(FBPost post, List<String> keywords, double result, FBPostKind kind) {
        this.post = post;
        this.keywords = keywords;
        this.result = result;
        this.kind = kind;
    }

    public FBPost getPost() {
        return post;
    }

    public void setPost(FBPost post) {
        this.post = post;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public FBPostKind getKind() {
        return kind;
    }

    public void setKind(FBPostKind kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "FBPostResult{" +
                "post=" + post +
                ", keywords=" + keywords +
                ", result=" + result +
                ", kind=" + kind +
                '}';
    }
}
