package com.wedt.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.wedt.model.FBPost;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReadPostsFromFile {

    public static ArrayList<FBPost> getPosts(String fileName) throws IOException {

        ArrayList<FBPost> posts = new ArrayList<>();
        String content = new String(Files.readAllBytes(Paths.get(fileName)));

        Gson g = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
                return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
            }
        }).create();

        return g.fromJson(content, new TypeToken<List<FBPost>>(){}.getType());
    }
}
