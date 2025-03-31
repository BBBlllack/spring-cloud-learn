package com.bbblllack.api.fallback;

import com.bbblllack.api.TodoClient;
import com.bbblllack.model.todo.Todo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 当feign超时执行此方法
 * 需要配置
 * feign:
 *   sentinel:
 *     enabled: true
 */
@Component
public class TodoClientFallback implements TodoClient {
    @Override
    public List<Todo> getTodoByUserId(Integer userId) {
        Todo todo = new Todo();
        todo.setId(0);
        todo.setTitle("未知事件");
        return List.of(todo);
    }
}
