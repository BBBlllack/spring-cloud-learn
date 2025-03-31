package com.bbblllack.api;

import com.bbblllack.api.fallback.TodoClientFallback;
import com.bbblllack.model.todo.Todo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-todo", fallback = TodoClientFallback.class)
public interface TodoClient {

    @GetMapping("/todo/{userId}")
    public List<Todo> getTodoByUserId(@PathVariable("userId") Integer userId);

}
