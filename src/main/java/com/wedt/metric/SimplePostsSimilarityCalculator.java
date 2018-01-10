package com.wedt.metric;

import com.wedt.analyzer.PostAnalyzer;
import com.wedt.model.FBPost;
import com.wedt.util.SynonymUtils;
import javafx.util.Pair;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SimplePostsSimilarityCalculator extends SimilarityCalculator<FBPost, Pair<FBPost, Double>> {

    @Override
    public Pair<FBPost, Double> calcSimilarity(FBPost selected, FBPost other) {
        Set<String> ss1, ss2;
        try {
            ss1 = generateSynonymSet(PostAnalyzer.generateKeywords(selected));
            ss2 = generateSynonymSet(PostAnalyzer.generateKeywords(other));
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair<>(other, 0.0);
        }

        double generalSimSum = 0.0;
        double reducedSimSum = 0.0;
        int keywordPairsCounter = 0;
        WordsSimilarityCalculator wsc = new WordsSimilarityCalculator();
        for (String w1 : ss1) {
            for (String w2 : ss2) {
                double similarity = wsc.calcSimilarity(w1, w2);
                generalSimSum += similarity;
                if (similarity > 0.0) {
                    reducedSimSum += similarity;
                    keywordPairsCounter++;
                }

            }
        }
        double generalSimilarity = 0.0;
        if (ss1.size() * ss2.size() != 0)
            generalSimilarity = generalSimSum / (double) (ss1.size() * ss2.size());
        double reducedSimilarity = 0.0;
        if (keywordPairsCounter != 0)
            reducedSimilarity = reducedSimSum / (double) keywordPairsCounter;
        double timeFactor = 0.0; // TODO maybe
        PostMatchingMeasure postMatchingMeasure = new PostMatchingMeasure(generalSimilarity, reducedSimilarity, keywordPairsCounter);

        double calcFinalSimilarity = postMatchingMeasure.getReducedSimilarity();
        // TODO - funkcja która z powyższych danych zwróci jakąś sensowną wartość
        return new Pair<>(other, calcFinalSimilarity * 100); // to percentage
    }

    protected Set<String> generateSynonymSet(Collection<String> list) {
        SynonymUtils synonymUtils = new SynonymUtils();
        return list.stream()
                .map(synonymUtils::getSynonymsSet)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
