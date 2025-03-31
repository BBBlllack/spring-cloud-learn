package com.bbblllack;

import com.bbblllack.api.PostClient;
import com.bbblllack.api.TodoClient;
import com.bbblllack.model.post.Post;
import com.bbblllack.model.todo.Todo;
import com.bbblllack.model.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class FutureTest {

    @Value("${jsonplaceholder.url}")
    private String jsonplaceholderUrl;

    @Autowired
    @Qualifier("nlb")
    private RestTemplate restTemplate;

    @Autowired
    private TodoClient todoClient;

    @Autowired
    private PostClient postClient;

    @Test
    void test01() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        Integer id = 1;
        User user = restTemplate.getForObject(jsonplaceholderUrl + "/users/{id}", User.class, id);
        List<Todo> todos = todoClient.getTodoByUserId(id);
        List<Post> posts = postClient.getPostsByUserId(id);

//        CompletableFuture<List<Todo>> todoFuture = CompletableFuture.supplyAsync(()->{
//            List<Todo> todos = todoClient.getTodoByUserId(id);
//            return todos;
//        });
//
//        CompletableFuture<List<Post>> postFuture = CompletableFuture.supplyAsync(()->{
//            List<Post> posts = postClient.getPostsByUserId(id);
//            return posts;
//        });
//
//        CompletableFuture<Void> allofFuture = CompletableFuture.allOf(todoFuture, postFuture);
//        allofFuture.get();
//        user.setTodos(todoFuture.get());
//        user.setPosts(postFuture.get()); // 650ms

        user.setTodos(todos);
        user.setPosts(posts); // 701ms

        System.out.println(System.currentTimeMillis() - startTime);
    }

    @Test
    void test02() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("ok1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("ok2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture.allOf(future1, future2).get();

        System.out.println(System.currentTimeMillis() - startTime);
    }
}
