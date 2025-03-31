package com.bbblllack.model.todo;

import lombok.Data;

@Data
public class Todo {
    private Integer id;
    private String userId;
    private String title;
    private Boolean completed;
}
