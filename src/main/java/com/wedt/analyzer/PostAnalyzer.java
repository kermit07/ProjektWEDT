package com.wedt.analyzer;

import com.wedt.model.FBPost;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.morfologik.MorfologikFilter;
import org.apache.lucene.analysis.pl.PolishAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.AttributeFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

public class PostAnalyzer {

    public static List<String> generateKeywords(FBPost post, int vectorLen) throws Exception {
        String message = post.getMsg();
        String cleanText = prepareMessage(cleanText(message));
        return mkSortedKeywordsList(cleanText, vectorLen);
    }

    private static String cleanText(String text) {
        return text.toLowerCase()
                .replaceAll("[\\p{Punct}&&[^'-]]+", ""); // usunięcie znaków interpunkcyjnych z wyjątkiem ' i -
    }

    private static String prepareMessage(String msg) throws IOException {
        return lemmatization(stopWords(msg));
    }

    private static String stopWords(String msg) throws IOException {
        StandardTokenizer tokenizer = new StandardTokenizer(AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY);
        tokenizer.setReader(new StringReader(msg));
        TokenStream tokenStream = new StopFilter(tokenizer, PolishAnalyzer.getDefaultStopSet());
        tokenStream.reset();
        return performFilter(tokenStream);
    }

    private static String lemmatization(String msg) throws IOException {
        StandardTokenizer tokenizer = new StandardTokenizer(AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY);
        tokenizer.setReader(new StringReader(msg));
        TokenStream tokenStream = new MorfologikFilter(tokenizer);
        tokenStream.reset();
        return performFilter(tokenStream);
    }

    private static String performFilter(TokenStream tokenStream) throws IOException {
        CharTermAttribute token = tokenStream.getAttribute(CharTermAttribute.class);
        StringBuilder sb = new StringBuilder();
        while (tokenStream.incrementToken())
            sb.append(token.toString()).append(" ");
        tokenStream.close();
        return sb.toString().trim();
    }

    private static List<String> mkSortedKeywordsList(String msg, int vSize) {
        Map<String, Integer> map = new HashMap<>();
        Arrays.stream(msg.split(" ")).forEach(word -> {
            if (map.containsKey(word))
                map.put(word, map.get(word) + 1);
            else
                map.put(word, 1);
        });

        List<Map.Entry<String, Integer>> mapList = new ArrayList<>();
        map.forEach(
                (key, value) -> mapList.add(new AbstractMap.SimpleEntry<String, Integer>(key, value))
        );
        mapList.sort(Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(mapList);

        return mapList.stream()
                .limit(vSize)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
