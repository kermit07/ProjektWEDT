package com.wedt.app;

import com.wedt.model.FBPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    ReadPostsFromFile readPostsFromFile;

    @RequestMapping(
            value = "/api/posts",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<FBPost> getPosts() {
        try {
            return ReadPostsFromFile.getPosts("fb_posts_test.json");
        } catch (IOException e) {
//            e.printStackTrace();
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
//            e.printStackTrace();
            return new FBPost(id, "message!", LocalDateTime.now());
        }
    }

    @RequestMapping(
            value = "/api/simple/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<FBPost> runSimple() { // TODO
        System.out.println("runSimple() ");
        List<FBPost> posts = new ArrayList<>();
        posts.add(new FBPost("123", "message1", LocalDateTime.now()));
        posts.add(new FBPost("234", "message2", LocalDateTime.now()));
        return posts;
    }

    @RequestMapping(
            value = "/api/advanced/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<FBPost> runAdvanced() { // TODO
        System.out.println("runAdvanced() ");
        List<FBPost> posts = new ArrayList<>();
        posts.add(new FBPost("123", "message1", LocalDateTime.now()));
        posts.add(new FBPost("234", "message2", LocalDateTime.now()));
        posts.add(new FBPost("345", "message3", LocalDateTime.now()));
        posts.add(new FBPost("789", "message3", LocalDateTime.now()));
        return posts;
    }
}
