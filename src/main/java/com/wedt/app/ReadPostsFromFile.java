package com.wedt.app;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.wedt.analyzer.PostAnalyzer;
import com.wedt.model.FBPost;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ReadPostsFromFile {

    public static List<FBPost> getPosts(String fileName, Set<String> dictList) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(Config.PROJECT_PATH + fileName)), "UTF-8");
        Gson g = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
                return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
            }
        }).create();

        List<FBPost> result = g.fromJson(content, new TypeToken<List<FBPost>>() {
        }.getType());

        if (dictList.isEmpty())
            return result;
        return result
                .stream()
                .map(p -> new FBPost(p.getId(), p.getMsg(), p.getDate(), PostAnalyzer.generateKeywords(p, dictList)))
                .filter(p -> p.getKeywords().size() > 0)
                .collect(Collectors.toList());
    }
}
