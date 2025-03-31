package com.bbblllack.api.fallback;

import com.bbblllack.api.PostClient;
import com.bbblllack.model.post.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostClientFallback implements PostClient {
    @Override
    public List<Post> getPostsByUserId(Integer id) {
        Post post = new Post();
        post.setId(0);
        post.setTitle("未知文章");
        return List.of(post);
    }
}
