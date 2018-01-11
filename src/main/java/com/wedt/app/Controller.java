package com.wedt.app;

import com.wedt.analyzer.PostAnalyzer;
import com.wedt.metric.ClassificationConfiguration;
import com.wedt.metric.PostsSimilarityCalculator;
import com.wedt.metric.PostsSimilarityMetricCalculator;
import com.wedt.metric.RepresentationConfiguration;
import com.wedt.model.FBPost;
import com.wedt.model.FBPostKind;
import com.wedt.model.FBPostResult;
import com.wedt.util.SynonymUtils;
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
    public List<FBPost> getPosts(@RequestParam(value = "limit", required = true) long limit,
                                 @RequestParam(value = "offset", required = true) long offset) {
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
            List<String> keywords = SynonymUtils.generateSynonymSet(PostAnalyzer.generateKeywords(foundPost))
                    .stream()
                    .collect(Collectors.toList());
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
    public List<FBPostResult> run(@PathVariable("id") String id,
                                  @RequestParam(value = "synonymEnabled", defaultValue = "false") boolean synonymEnabled,
                                  @RequestParam(value = "dictionaryEnabled", defaultValue = "false") boolean dictionaryEnabled,
                                  @RequestParam(value = "kindEnabled", defaultValue = "false") boolean kindEnabled) {
        RepresentationConfiguration rc = RepresentationConfiguration.SIMPLE;
        if (synonymEnabled)
            rc = RepresentationConfiguration.ADVANCED;
        ClassificationConfiguration cc = new ClassificationConfiguration(dictionaryEnabled, kindEnabled);
        System.setProperty("wordnet.database.dir", Config.WORDNET_DIST_DIR);
        try {
            List<FBPost> allPosts = ReadPostsFromFile.getPosts("fb_posts_test.json")
                    .stream()
                    .limit(20)
                    .collect(Collectors.toList());
            FBPost selectedPost = this.getPost(id).getPost();
            PostsSimilarityMetricCalculator metric = new PostsSimilarityMetricCalculator(new PostsSimilarityCalculator(rc, cc));
            return metric.run(selectedPost, allPosts)
                    .stream()
                    .limit(20)
                    .sorted((e1, e2) -> Double.compare(e2.getResult(), e1.getResult()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
