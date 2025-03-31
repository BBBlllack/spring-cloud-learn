package com.bbblllack.controller;

import com.bbblllack.api.CommentClient;
import com.bbblllack.api.PostApi;
import com.bbblllack.model.post.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostApi postApi;

    @Autowired
    private CommentClient commentClient;

    @Value("${jsonplaceholder.url}")
    private String jsonplaceholderUrl;

    @GetMapping("/{userId}")
    public List<Post> getPost (@PathVariable("userId") Integer userId) {
        List<Post> posts = postApi.getPostsByUserId(userId);
        posts.forEach(post -> {
            post.setComments(commentClient.getCommentsByPostId(post.getId()));
        });
        return posts;
    }

}
