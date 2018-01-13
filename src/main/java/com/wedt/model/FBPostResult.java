package com.wedt.model;

import javafx.util.Pair;
import java.util.Set;

public class FBPostResult {
    private FBPost post;
    private Set<Pair<String, Double>> similarity;
    private double result;
    private FBPostKind kind;

    public FBPostResult(FBPost post, Set<Pair<String, Double>> keywords, double result, FBPostKind kind) {
        this.post = post;
        this.similarity = keywords;
        this.result = result;
        this.kind = kind;
    }

    public FBPost getPost() {
        return post;
    }

    public void setPost(FBPost post) {
        this.post = post;
    }

    public Set<Pair<String, Double>> getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Set<Pair<String, Double>> similarity) {
        this.similarity = similarity;
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
                ", similarity=" + similarity +
                ", result=" + result +
                ", kind=" + kind +
                '}';
    }
}
