package com.wedt.app;

import com.wedt.metric.PostsSimilarityMetricCalculator;
import com.wedt.metric.SimplePostsSimilarityCalculator;
import com.wedt.model.FBPost;
import com.wedt.util.ReadPostsFromFile;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {

        // SpringApplication.run(AppApplication.class, args);

        // Inicjalizacja słownika WordNet
        System.setProperty("wordnet.database.dir", Config.WORDNET_DIST_DIR);
        ArrayList<FBPost> posts;
        try {
            posts = ReadPostsFromFile.getPosts("fb_posts_test.json");
        } catch (IOException e) {
            return;
        }

        SimplePostsSimilarityCalculator simple = new SimplePostsSimilarityCalculator();
        PostsSimilarityMetricCalculator calc = new PostsSimilarityMetricCalculator(simple);

        FBPost selectedPost = posts
                .stream()
                .filter(p -> p.getId().equals("784877008193417_1875602095787564"))
                .findFirst()
                .get();

        calc.run(selectedPost, posts);
    }
}
