package com.bbblllack.api;

import com.bbblllack.model.comment.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "comment-api", url = "${jsonplaceholder.url}")
public interface CommentApi {

    @GetMapping("/posts/{id}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable("id") Integer id);
}
