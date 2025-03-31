package com.bbblllack.api;

import com.bbblllack.api.fallback.PostClientFallback;
import com.bbblllack.model.post.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-post", fallback = PostClientFallback.class)
public interface PostClient {

    @GetMapping("/post/{id}")
    public List<Post> getPostsByUserId(@PathVariable("id") Integer id);
}
