package com.wedt.metric;

import com.wedt.analyzer.PostAnalyzer;
import com.wedt.model.FBPost;
import com.wedt.model.FBPostKind;
import com.wedt.model.FBPostResult;
import com.wedt.util.SynonymUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PostsSimilarityCalculator extends SimilarityCalculator<FBPost, FBPostResult> {

    public PostsSimilarityCalculator(RepresentationConfiguration rc, ClassificationConfiguration cc) {
        super(rc, cc);
    }

    @Override
    public FBPostResult calcSimilarity(FBPost selected, FBPost other) {
        Set<String> ss1, ss2;
        try {
            if (this.rc.equals(RepresentationConfiguration.SIMPLE)) {
                ss1 = PostAnalyzer.generateKeywords(selected);
                ss2 = PostAnalyzer.generateKeywords(other);
            } else {
                ss1 = SynonymUtils.generateSynonymSet(PostAnalyzer.generateKeywords(selected));
                ss2 = SynonymUtils.generateSynonymSet(PostAnalyzer.generateKeywords(other));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new FBPostResult(other, new ArrayList<>(), 0.0, FBPostKind.UNKNOWN);
        }

        if(this.cc.isDictionaryEnabled()) {
            // wtedy ładujemy słownik, czyli grupy podobnych znaczeń
        }

        if(this.cc.isKindEnabled()) {
            // TODO na końcu
            // jeśli tak to próbujemy klasyfikować posty pod względem ogłoszeń i zgłoszeń kandydatów
        }

        double reducedSimSum = 0.0;
        int keywordPairsCounter = 0;
        WordsSimilarityCalculator wsc = new WordsSimilarityCalculator();
        for (String w1 : ss1) {
            for (String w2 : ss2) {
                double similarity = wsc.calcSimilarity(w1, w2);
                if (similarity > 0.0) {
                    System.out.println("similarity [" + w1 + "] [" + w2 + "] = " + similarity);
                    reducedSimSum += similarity;
                    keywordPairsCounter++;
                }

            }
        }
        double reducedSimilarity = 0.0;
        if (keywordPairsCounter != 0)
            reducedSimilarity = reducedSimSum / (double) keywordPairsCounter;

        return new FBPostResult(other, ss2.stream().collect(Collectors.toList()), reducedSimilarity, FBPostKind.UNKNOWN);
    }

}