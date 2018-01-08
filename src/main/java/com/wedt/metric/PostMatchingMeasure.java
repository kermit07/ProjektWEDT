package com.wedt.metric;

public class PostMatchingMeasure {

    private double generalSimilarity;   // standardowe podobieństwo - każde słowo z każdym słowem
    private double reducedSimilarity;   // pomijane są wszystkie pary słów niepodobnych
    private int keywordsPairCounter;    // liczba par słów pasujących - podobieństwo > 0
    private double advancedSimilarity;  // brany pod uwagę słownik
    private double timeFactor;          // współczynnik odchylenia dat

    public PostMatchingMeasure(double generalSimilarity, double reducedSimilarity, int keywordsPairCounter) {
        this.generalSimilarity = generalSimilarity;
        this.reducedSimilarity = reducedSimilarity;
        this.keywordsPairCounter = keywordsPairCounter;
    }

    public PostMatchingMeasure(double generalSimilarity, double reducedSimilarity, int keywordsPairCounter, double advancedSimilarity) {
        this.generalSimilarity = generalSimilarity;
        this.reducedSimilarity = reducedSimilarity;
        this.keywordsPairCounter = keywordsPairCounter;
        this.advancedSimilarity = advancedSimilarity;
    }

    public double getGeneralSimilarity() {
        return generalSimilarity;
    }

    public double getReducedSimilarity() {
        return reducedSimilarity;
    }

    public double getAdvancedSimilarity() {
        return advancedSimilarity;
    }

    public int getKeywordsPairCounter() {
        return keywordsPairCounter;
    }

    public double getTimeFactor() {
        return timeFactor;
    }
}
