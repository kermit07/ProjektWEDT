package com.wedt.metric;

import com.wedt.model.FBPost;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class PostsSimilarityMetricCalculator {
    private SimilarityCalculator<FBPost, Pair<FBPost, Double>> similarityCalculator;

    public PostsSimilarityMetricCalculator(SimilarityCalculator<FBPost, Pair<FBPost, Double>> similarityCalculator) {
        this.similarityCalculator = similarityCalculator;
    }

    public List<Pair<FBPost, Double>> run(FBPost selectedPost, List<FBPost> allPosts) {
        List<Pair<FBPost, Double>> result = new ArrayList<>();
        for (FBPost post : allPosts)
            result.add(similarityCalculator.calcSimilarity(selectedPost, post));
        return result;
    }
}
