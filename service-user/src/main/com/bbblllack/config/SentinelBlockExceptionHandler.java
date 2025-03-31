package com.bbblllack.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.bbblllack.model.common.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class SentinelBlockExceptionHandler implements BlockExceptionHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s, BlockException e) throws Exception {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        PrintWriter writer = httpServletResponse.getWriter();

        writer.write(objectMapper.writeValueAsString(ApiResponse.error(String.format("sentinel error: %s", e.getMessage()))));
        writer.flush();
        writer.close();
    }
}
