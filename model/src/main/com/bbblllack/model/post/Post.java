package com.bbblllack.model.post;

import com.bbblllack.model.comment.Comment;
import lombok.Data;

import java.util.List;

@Data
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
    private List<Comment> comments;
}
