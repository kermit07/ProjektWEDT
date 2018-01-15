package com.wedt.analyzer;

import com.wedt.app.Config;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PostAnalyzer {

    public static Set<String> generateKeywords(FBPost post, Set<String> dictList) {
        String message = post.getMsg();
        try {
            String preparedMessage = prepareMessage(cleanText(message));
            return mkKeywordsList(preparedMessage, dictList);
        } catch (IOException e) {
            return new HashSet<>();
        }
    }

    private static String cleanText(String text) {
        return text.toLowerCase()
                .replaceAll("[\\p{Punct}&&[^'-]]+", " "); // usunięcie znaków interpunkcyjnych z wyjątkiem ' i -
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

    private static Set<String> mkKeywordsList(String msg, Set<String> dictList) {
        Set<String> map = new HashSet<>();
        Arrays.stream(msg.split(" ")).forEach(word -> {
            if (dictList.contains(word))
                map.add(word);
        });
        return map.stream()
                .limit(Config.VECTOR_LIMIT)
                .collect(Collectors.toSet());
    }

}
