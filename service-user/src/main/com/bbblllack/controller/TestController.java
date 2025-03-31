package com.bbblllack.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.context.ContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("/user/test/")
public class TestController {

    private static AtomicLong counter = new AtomicLong(0);


    @SentinelResource(value = "addCount", blockHandler = "countAddHandler")
    private void add() {
        counter.incrementAndGet();
    }

    private void countAddHandler() {
        System.out.println("countAddHandler");
    }

    @GetMapping("/count/add")
    public long countAdd() {
        add();
        return counter.get();
    }
}
