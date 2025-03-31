package com.bbblllack.api;

import com.bbblllack.model.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "post-api", url = "${jsonplaceholder.url}")
public interface PostApi {

    @GetMapping("/users/{id}/posts")
    public List<Post> getPostsByUserId(@PathVariable("id") Integer userId);
}
