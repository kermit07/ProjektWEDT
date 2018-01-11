package com.wedt.metric;

import com.wedt.model.FBPost;
import javafx.util.Pair;

import java.time.LocalDateTime;

public class AdvancedPostsSimilarityCalculator extends SimilarityCalculator<FBPost, Pair<FBPost, Double>> {

    @Override
    public Pair<FBPost, Double> calcSimilarity(FBPost selected, FBPost other) {
        return new Pair<>(new FBPost("", "", LocalDateTime.now()), 0.0);
    }

}