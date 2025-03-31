package com.bbblllack.model.comment;

import lombok.Data;

/**
 * {
 * "postId": 2,
 * "id": 6,
 * "name": "et fugit eligendi deleniti quidem qui sint nihil autem",
 * "email": "Presley.Mueller@myrl.com",
 * "body": "doloribus at sed quis culpa deserunt consectetur qui praesentium\naccusamus fugiat dicta\nvoluptatem rerum ut voluptate autem\nvoluptatem repellendus aspernatur dolorem in"
 * },
 */
@Data
public class Comment {
    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;
}
