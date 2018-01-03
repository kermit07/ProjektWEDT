package com.wedt.util;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SynonymUtils {

    private WordNetDatabase database;

    public SynonymUtils() {
        database = WordNetDatabase.getFileInstance();
    }

    public Set<String> getSynonymsSet(String word) {
        Synset[] possibleSynonymsSets = getPossibleSynonymsSets(word);
        if (possibleSynonymsSets == null || possibleSynonymsSets.length == 0) {
            return Collections.singleton(word);
        }
        return Arrays.stream(possibleSynonymsSets)
                .map(this::createSynonymListFromSynset)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    private Synset[] getPossibleSynonymsSets(String word) {
        try {
            return database.getSynsets(word);
        } catch (Exception e) {
            System.out.println("Failed to find synset.");
        }
        return null;
    }

    private List<String> createSynonymListFromSynset(Synset synset) {
        if (synset == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(synset.getWordForms())
                .map(this::encodeWithUtf8)
                .collect(Collectors.toList());
    }

    private String encodeWithUtf8(String word) {
        String utf8String = null;
        try {
            byte[] ptext = word.getBytes("ISO_8859_1");
            utf8String = new String(ptext, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return utf8String;
    }
}
