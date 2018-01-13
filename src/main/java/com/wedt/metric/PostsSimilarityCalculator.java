package com.wedt.metric;

import com.wedt.analyzer.PostAnalyzer;
import com.wedt.model.FBPost;
import com.wedt.model.FBPostKind;
import com.wedt.model.FBPostResult;
import com.wedt.util.SynonymUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class PostsSimilarityCalculator extends SimilarityCalculator<FBPost, FBPostResult> {

    public PostsSimilarityCalculator(RepresentationConfiguration rc, ClassificationConfiguration cc, Set<String> dictList) {
        super(rc, cc, dictList);
    }

    @Override
    public FBPostResult calcSimilarity(FBPost selected, FBPost other) {
        Set<String> ss1, ss2;
        try {
            if (this.rc.equals(RepresentationConfiguration.SIMPLE)) {
                ss1 = PostAnalyzer.generateKeywords(selected, this.dictList);
                ss2 = PostAnalyzer.generateKeywords(other, this.dictList);
            } else {
                ss1 = SynonymUtils.generateSynonymSet(PostAnalyzer.generateKeywords(selected, this.dictList));
                ss2 = SynonymUtils.generateSynonymSet(PostAnalyzer.generateKeywords(other, this.dictList));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new FBPostResult(other, new ArrayList<>(), 0.0, FBPostKind.UNKNOWN);
        }


        if (this.cc.isKindEnabled()) {
            // TODO na końcu
            // jeśli tak to próbujemy klasyfikować posty pod względem ogłoszeń i zgłoszeń kandydatów
        }

        double similaritySum = 0.0;
        WordsSimilarityCalculator wsc = new WordsSimilarityCalculator();
        for (String w1 : ss1) {
            for (String w2 : ss2) {
                double similarity = wsc.calcSimilarity(w1, w2);
                if (similarity > 0.0)
                    similaritySum += similarity;
            }
        }

        if (this.cc.isDictionaryEnabled()) {
            // TODO wtedy ładujemy słownik, czyli grupy podobnych znaczeń i zwiększamy dopasiwanie jeśli znaleźliśmy znaczenie synonimowe
        }

        return new FBPostResult(other, ss2.stream().collect(Collectors.toList()), similaritySum, FBPostKind.UNKNOWN);
    }

}