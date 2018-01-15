package com.wedt.metric;

import com.wedt.app.ReadDictFromFile;
import com.wedt.model.FBPost;
import com.wedt.model.FBPostKind;
import com.wedt.model.FBPostResult;
import com.wedt.util.SynonymUtils;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostsSimilarityCalculator extends SimilarityCalculator<FBPost, FBPostResult> {

    public PostsSimilarityCalculator(RepresentationConfiguration rc, ClassificationConfiguration cc) {
        super(rc, cc);
    }

    @Override
    public FBPostResult calcSimilarity(FBPost selected, FBPost other) {

        FBPostKind postKind = FBPostKind.UNKNOWN;
        List<Set<String>> dictionary = new ArrayList<>();
        if (this.cc.isDictionaryEnabled()) {
            try {
                dictionary = ReadDictFromFile.getDict("dict.txt");
            } catch (IOException e) {

            }
        }

        if (this.cc.isKindEnabled()) {
            FBPostKind selectedPostKind = PostKindCalculator.calculatePostKind(selected);
            FBPostKind otherPostKind = PostKindCalculator.calculatePostKind(other);

            if (selectedPostKind == FBPostKind.UNKNOWN || otherPostKind == FBPostKind.UNKNOWN || selectedPostKind == otherPostKind) {
                return new FBPostResult(other, new HashSet<>(), 0.0, otherPostKind);
            } else {
                postKind = otherPostKind;
            }
        }

        Set<String> keyWords = selected.getKeywords();
        if (this.rc == RepresentationConfiguration.ADVANCED) {
            Set<String> keyWordsSynonyms = SynonymUtils.generateSynonymSet(selected.getKeywords());
            keyWords.addAll(keyWordsSynonyms);
        }

        Set<Pair<String, Double>> similarity = new HashSet<>();
        double similaritySum = 0.0;
        WordsSimilarityCalculator wsc = new WordsSimilarityCalculator();
        for (String w1 : keyWords) {
            for (String w2 : other.getKeywords()) {
                double sim = wsc.calcSimilarity(w1, w2);
                if (sim > 0.0) {
                    similaritySum += sim;
                    similarity.add(new Pair<>("(" + w1 + "," + w2 + ")", sim));
                }

                if (sim < 1.0 && this.cc.isDictionaryEnabled()) {
                    for (Set<String> set : dictionary) {
                        if (set.contains(w1) && set.contains(w2)) {
                            similaritySum += 1;
                            similarity.add(new Pair<>("[" + w1 + "," + w2 + "]", 1.0));
                        }
                    }

                }
            }
        }

        return new FBPostResult(other, similarity, similaritySum, postKind);
    }
}