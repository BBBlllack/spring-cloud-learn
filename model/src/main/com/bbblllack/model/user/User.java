package com.bbblllack.model.user;

import com.bbblllack.model.post.Post;
import com.bbblllack.model.todo.Todo;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private List<Todo> todos;
    private List<Post> posts;
}
