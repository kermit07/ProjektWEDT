package com.wedt.metric;

import com.wedt.app.Config;
import info.debatty.java.stringsimilarity.Levenshtein;

public class WordsSimilarityCalculator {

    // zwraca wartość z przedziału <0,1>. 0 jeśli słowa nie są podobne a 1 jeśli są identyczne
    public static double calcSimilarity(String s1, String s2) {
        if (s1.length() < Config.MIN_REASONABLE_WORD_LENGTH || s2.length() < Config.MIN_REASONABLE_WORD_LENGTH)
            return 0.0;
        if (s2.length() >= Config.LEVENSHTEIN_LENGTH_MIN)
            return calcLevenshteinFactor(s1, s2);
        return calcNumberOfCharsContaining(s1, s2) / (double) getShorter(s1, s2).length();
    }

    private static String getShorter(String s1, String s2) {
        return s1.length() < s2.length() ? s1 : s2;
    }

    // zwraca liczbę identycznych znaków początkowych dla dwóch słów
    private static int calcNumberOfCharsContaining(String s1, String s2) {
        int chars = Config.MIN_REASONABLE_WORD_LENGTH;
        if (s1.substring(0, chars).equals(s2.substring(0, chars))) {
            while (s1.length() > chars && s2.length() > chars && s1.charAt(chars) == s2.charAt(chars))
                chars++;
            return chars;
        }
        return 0;
    }

    private static double calcLevenshteinFactor(String s1, String s2) {
        Levenshtein l = new Levenshtein();
        double distance = l.distance(s1, s2);
        if (distance < Config.LEVENSHTEIN_MIN)
            return 1.0 - distance / Config.LEVENSHTEIN_MIN;
        return 0.0;
    }

}
