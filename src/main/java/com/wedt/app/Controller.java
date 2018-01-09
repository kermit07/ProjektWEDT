package com.wedt.app;

import com.wedt.model.FBPost;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class Controller {

    @RequestMapping(
            value = "/api/posts",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<FBPost> getPosts() {
        List<FBPost> posts = new ArrayList<>();
        posts.add(new FBPost("123", "message1", LocalDateTime.now()));
        posts.add(new FBPost("234", "message2", LocalDateTime.now()));
        posts.add(new FBPost("345", "message3", LocalDateTime.now()));
        return posts;
    }

    @RequestMapping(
            value = "/api/post/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public FBPost getPost(@PathVariable("id") String id) {
        System.out.println("getPost() " + id);
        return new FBPost(id, "message!", LocalDateTime.now());
    }

    @RequestMapping(
            value = "/api/simple/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<FBPost> runSimple() {
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
    public List<FBPost> runAdvanced() {
        System.out.println("runAdvanced() ");
        List<FBPost> posts = new ArrayList<>();
        posts.add(new FBPost("123", "message1", LocalDateTime.now()));
        posts.add(new FBPost("234", "message2", LocalDateTime.now()));
        posts.add(new FBPost("345", "message3", LocalDateTime.now()));
        posts.add(new FBPost("789", "message3", LocalDateTime.now()));
        return posts;
    }
}
