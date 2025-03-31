package com.bbblllack.controller;

import com.bbblllack.api.CommentApi;
import com.bbblllack.model.comment.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentApi commentApi;

    @GetMapping("/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable("postId") Integer id){
        return commentApi.getCommentsByPostId(id);
    }
}
