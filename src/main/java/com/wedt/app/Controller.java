package com.wedt.app;

import com.wedt.metric.AdvancedPostsSimilarityCalculator;
import com.wedt.metric.PostsSimilarityMetricCalculator;
import com.wedt.metric.SimplePostsSimilarityCalculator;
import com.wedt.model.FBPost;
import javafx.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    @RequestMapping(value = "/api/posts",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<FBPost> getPosts(@RequestParam(value = "limit", required = true) long limit, @RequestParam(value = "offset", required = true) long offset) {
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
    public FBPost getPost(@PathVariable("id") String id) {
        try {
            return ReadPostsFromFile.getPosts("fb_posts_test.json")
                    .stream()
                    .filter(post -> post.getId().equals(id))
                    .findFirst()
                    .get();
        } catch (IOException e) {
            return null;
        }
    }

    @RequestMapping(
            value = "/api/simple/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<Pair<FBPost, Double>> runSimple(@PathVariable("id") String id) {
        try {
            ArrayList<FBPost> allPosts = ReadPostsFromFile.getPosts("fb_posts_test.json");
            FBPost selectedPost = this.getPost(id);
            PostsSimilarityMetricCalculator metric = new PostsSimilarityMetricCalculator(new SimplePostsSimilarityCalculator());
            return metric.run(selectedPost, allPosts)
                    .stream()
                    .limit(20)
                    .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @RequestMapping(
            value = "/api/advanced/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<Pair<FBPost, Double>> runAdvanced(@PathVariable("id") String id) {
        try {
            ArrayList<FBPost> allPosts = ReadPostsFromFile.getPosts("fb_posts_test.json");
            FBPost selectedPost = this.getPost(id);

            PostsSimilarityMetricCalculator metric = new PostsSimilarityMetricCalculator(new AdvancedPostsSimilarityCalculator());
            return metric.run(selectedPost, allPosts)
                    .stream()
                    .limit(20)
                    .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
