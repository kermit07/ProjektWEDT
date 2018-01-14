package com.wedt.metric;

import com.wedt.model.FBPost;
import com.wedt.model.FBPostKind;
import com.wedt.model.FBPostResult;
import javafx.util.Pair;
import org.apache.lucene.search.similarities.Similarity;

import java.util.HashSet;
import java.util.Set;

public class PostsSimilarityCalculator extends SimilarityCalculator<FBPost, FBPostResult> {

    public PostsSimilarityCalculator(RepresentationConfiguration rc, ClassificationConfiguration cc) {
        super(rc, cc);
    }

    @Override
    public FBPostResult calcSimilarity(FBPost selected, FBPost other) {
        if (this.cc.isKindEnabled()) {
            // TODO na końcu
            // jeśli tak to próbujemy klasyfikować posty pod względem ogłoszeń i zgłoszeń kandydatów
        }

        Set<Pair<String, Double>> similarity = new HashSet<>();
        double similaritySum = 0.0;
        WordsSimilarityCalculator wsc = new WordsSimilarityCalculator();
        for (String w1 : selected.getKeywords()) {
            for (String w2 : other.getKeywords()) {
                double sim = wsc.calcSimilarity(w1, w2);
                if (sim > 0.0) {
                    similaritySum += sim;
                    similarity.add(new Pair<>("[" + w1 + "," + w2 + "]", sim));
                }
            }
        }

        if (this.cc.isDictionaryEnabled()) {
            // TODO wtedy ładujemy słownik, czyli grupy podobnych znaczeń i zwiększamy dopasiwanie jeśli znaleźliśmy znaczenie synonimowe
        }

        return new FBPostResult(other, similarity, similaritySum, FBPostKind.UNKNOWN);
    }

}