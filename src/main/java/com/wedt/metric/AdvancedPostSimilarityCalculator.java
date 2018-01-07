package com.wedt.metric;

import com.wedt.app.Config;
import com.wedt.model.FBPost;

public class AdvancedPostSimilarityCalculator extends SimilarityCalculator<FBPost, FBPost> {

    @Override
    double calcSimilarity(FBPost p1, FBPost p2) {
        return 0.0; // TODO
    }
}
