package com.bbblllack.controller;

import com.bbblllack.annotations.ConsumeTime;
import com.bbblllack.api.PostClient;
import com.bbblllack.api.TodoClient;
import com.bbblllack.model.common.ApiResponse;
import com.bbblllack.model.post.Post;
import com.bbblllack.model.todo.Todo;
import com.bbblllack.model.user.UserStatus;
import com.bbblllack.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Slf4j
@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${jsonplaceholder.url}")
    private String jsonplaceholderUrl;

    @Autowired
    @Qualifier("nlb")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("lb")
    private RestTemplate restTemplateWithLB;

    @Autowired
    private TodoClient todoClient;

    @Autowired
    private PostClient postClient;

    @GetMapping("/convert")
    public Integer convertTest(@RequestParam("status") UserStatus userStatus) {
        return userStatus.getValue();
    }

    @GetMapping("/jsonurl")
    public ApiResponse<String> getJsonUrl() {
        return ApiResponse.success(jsonplaceholderUrl);
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable("id") Integer id) {
        User user = restTemplate.getForObject(jsonplaceholderUrl + "/users/{id}", User.class, id);
        return ApiResponse.success(user);
    }

    @GetMapping("/todo/{id}")
    public ApiResponse<User> getUserAndTodosById(@PathVariable("id") Integer id) {
        User user = restTemplate.getForObject(jsonplaceholderUrl + "/users/{id}", User.class, id);
//        List<Todo> todos = restTemplateWithLB
//                .getForObject("http://service-todo/todo/{id}", List.class, id);
        List<Todo> todos = todoClient.getTodoByUserId(id);
        user.setTodos(todos);
        return ApiResponse.success(user);
    }

    @GetMapping("/post/{id}")
    public ApiResponse<User> getUserAndPostsById(@PathVariable("id") Integer id) {
        User user = restTemplate.getForObject(jsonplaceholderUrl + "/users/{id}", User.class, id);
        List<Post> posts = postClient.getPostsByUserId(id);
        user.setPosts(posts);
        return ApiResponse.success(user);
    }

    @ConsumeTime
    @GetMapping("/all/{id}")
    public ApiResponse<User> getUsersAllInfo(@RequestHeader(value = "Token", required = false) String token,
                                             @PathVariable("id") Integer id) throws ExecutionException, InterruptedException {
        log.info("token: {}", token);
        User user = restTemplate.getForObject(jsonplaceholderUrl + "/users/{id}", User.class, id);
//        List<Todo> todos = todoClient.getTodoByUserId(id);
//        List<Post> posts = postClient.getPostsByUserId(id);

        CompletableFuture<List<Todo>> todoFuture = CompletableFuture.supplyAsync(()->{
            List<Todo> todos = todoClient.getTodoByUserId(id);
            return todos;
        });

        CompletableFuture<List<Post>> postFuture = CompletableFuture.supplyAsync(()->{
            List<Post> posts = postClient.getPostsByUserId(id);
            return posts;
        });

        CompletableFuture<Void> allofFuture = CompletableFuture.allOf(todoFuture, postFuture);
        allofFuture.get();
        user.setTodos(todoFuture.get());
        user.setPosts(postFuture.get());
        return ApiResponse.success(user);
    }


}
