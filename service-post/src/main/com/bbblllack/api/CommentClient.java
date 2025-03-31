package com.bbblllack.api;

import com.bbblllack.model.comment.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-comment")
public interface CommentClient {

    @GetMapping("/comment/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable("postId") Integer postId);
}
