package com.bbblllack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/todo")
public class TodoController {

    @Value("${jsonplaceholder.url}")
    private String jsonplaceholderUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public List getTodoByUserId(@PathVariable("userId") Integer userId){
        return restTemplate
                .getForObject(jsonplaceholderUrl + "/users/{userid}/todos",
                        List.class, userId);
    }
}
