package com.wedt.app;

import com.wedt.analyzer.PostAnalyzer;
import com.wedt.model.FBPost;
import com.wedt.model.FBPostResult;
import com.wedt.model.FBPostKind;
import com.wedt.util.SynonymUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class Controller {

    @RequestMapping(value = "/api/posts",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<FBPost> getPosts(@RequestParam(value = "limit", required = true) long limit, @RequestParam(value = "offset", required = true) long offset) {
        System.setProperty("wordnet.database.dir", Config.WORDNET_DIST_DIR);
        try {
            return ReadPostsFromFile.getPosts("fb_posts_test.json")
                    .stream()
                    .skip(offset)
                    .limit(limit)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @RequestMapping(
            value = "/api/post/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public FBPostResult getPost(@PathVariable("id") String id) {
        System.setProperty("wordnet.database.dir", Config.WORDNET_DIST_DIR);
        try {
            FBPost foundPost = ReadPostsFromFile.getPosts("fb_posts_test.json")
                    .stream()
                    .filter(post -> post.getId().equals(id))
                    .findFirst()
                    .get();
            List<String> keywords = PostAnalyzer.generateKeywords(foundPost);
            return new FBPostResult(foundPost, keywords, 0.0, FBPostKind.UNKNOWN);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(
            value = "/api/run/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<FBPostResult> run(@PathVariable("id") String id) {
        List<FBPostResult> result = new ArrayList<>();
        List<String> keywords = new ArrayList<>();
        keywords.add("słowo");
        keywords.add("kluczowe");
        keywords.add("przykładowe");
        result.add(new FBPostResult(new FBPost("1", "message1", LocalDateTime.now()), keywords, 55.12, FBPostKind.UNKNOWN));
        result.add(new FBPostResult(new FBPost("2", "message2", LocalDateTime.now()), keywords, 33.32, FBPostKind.UNKNOWN));
        result.add(new FBPostResult(new FBPost("3", "message3", LocalDateTime.now()), keywords, 28.51, FBPostKind.UNKNOWN));
        return result;
//        System.setProperty("wordnet.database.dir", Config.WORDNET_DIST_DIR);
//        try {
//            ArrayList<FBPost> allPosts = ReadPostsFromFile.getPosts("fb_posts_test.json");
//            FBPost selectedPost = this.getPost(id);
//            PostsSimilarityMetricCalculator metric = new PostsSimilarityMetricCalculator(new SimplePostsSimilarityCalculator());
//            return metric.run(selectedPost, allPosts)
//                    .stream()
//                    .limit(20)
//                    .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
//                    .collect(Collectors.toList());
//        } catch (IOException e) {
//            return new ArrayList<>();
//        }
    }
}
