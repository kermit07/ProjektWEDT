package com.wedt.app;

import com.wedt.analyzer.PostAnalyzer;
import com.wedt.metric.*;
import com.wedt.model.FBPost;
import com.wedt.model.FBPostKind;
import com.wedt.model.FBPostResult;
import com.wedt.util.SynonymUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class Controller {

    @RequestMapping(value = "/api/posts",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<FBPost> getPosts(@RequestParam(value = "limit", defaultValue = "20") long limit,
                                 @RequestParam(value = "offset", defaultValue = "0") long offset) {
        System.setProperty("wordnet.database.dir", Config.WORDNET_DIST_DIR);
        try {
            return ReadPostsFromFile.getPosts("fb_posts_test.json", new HashSet<>())
                    .stream()
                    .skip(offset)
                    .limit(limit)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
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
            Set<String> dictList = ReadStringSetFromFile.getDict("dict_list.txt");
            FBPost foundPost = ReadPostsFromFile.getPosts("fb_posts_test.json", dictList)
                    .stream()
                    .filter(post -> post.getId().equals(id))
                    .findFirst()
                    .get();
            return new FBPostResult(foundPost, new HashSet<>(), 0.0, PostKindCalculator.calculatePostKind(foundPost));
        } catch (Exception e) {
            e.printStackTrace();
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
                                  @RequestParam(value = "dictEnabled", defaultValue = "false") boolean dictionaryEnabled,
                                  @RequestParam(value = "kindEnabled", defaultValue = "false") boolean kindEnabled,
                                  @RequestParam(value = "limit", defaultValue = "20") long limit,
                                  @RequestParam(value = "offset", defaultValue = "0") long offset) {
        System.setProperty("wordnet.database.dir", Config.WORDNET_DIST_DIR);

        RepresentationConfiguration rc = RepresentationConfiguration.SIMPLE;
        if (synonymEnabled)
            rc = RepresentationConfiguration.ADVANCED;
        ClassificationConfiguration cc = new ClassificationConfiguration(dictionaryEnabled, kindEnabled);

        try {
            Set<String> dictList = ReadStringSetFromFile.getDict("dict_list.txt");
            List<FBPost> allPosts = ReadPostsFromFile.getPosts("fb_posts_test.json", dictList);

            FBPost selectedPost = this.getPost(id).getPost();
            PostsSimilarityMetricCalculator metric = new PostsSimilarityMetricCalculator(new PostsSimilarityCalculator(rc, cc));
            return metric.run(selectedPost, allPosts)
                    .stream()
                    .sorted((e1, e2) -> Double.compare(e2.getResult(), e1.getResult()))
                    .skip(offset)
                    .limit(limit)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
