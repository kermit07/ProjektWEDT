package com.wedt.metric;

import com.wedt.model.FBPost;
import com.wedt.model.FBPostResult;

import java.util.ArrayList;
import java.util.List;

public class PostsSimilarityMetricCalculator {
    private SimilarityCalculator<FBPost, FBPostResult> similarityCalculator;

    public PostsSimilarityMetricCalculator(SimilarityCalculator<FBPost, FBPostResult> similarityCalculator) {
        this.similarityCalculator = similarityCalculator;
    }

    public List<FBPostResult> run(FBPost selectedPost, List<FBPost> allPosts) {
        List<FBPostResult> result = new ArrayList<>();
        for (FBPost post : allPosts) {
            FBPostResult res = similarityCalculator.calcSimilarity(selectedPost, post);
            if (!selectedPost.getId().equals(post.getId()) && res.getResult() > 0.0)
                result.add(res);
        }
        return result;
    }
}
