package com.bbblllack.controller;

import com.bbblllack.api.UserClient;
import com.bbblllack.model.common.ApiResponse;
import com.bbblllack.model.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ForumController {

    @Autowired
    private UserClient userClient;

    private static Map<String, String> userUrls = new HashMap<>();

    @GetMapping
    public ApiResponse<User> redirctUserUrl(@RequestHeader("Host") String host) {
        log.info(host);
        String user = host.toLowerCase().split("\\.")[0];
        if (!userUrls.containsKey(user)) {
            return ApiResponse.success(new User());
        }
        return userClient.getUsersAllInfo(DigestUtils.md5DigestAsHex(user.getBytes()), Integer.valueOf(userUrls.get(user)));
    }

    @GetMapping("/reg")
    public ApiResponse<String> reg(@RequestParam("user") String user, @RequestParam("userId") String userId) {
        if (userUrls.containsKey(user)) {
            return ApiResponse.error("user already exists");
        }
        if (userUrls.containsValue(userId)) {
            return ApiResponse.error("userId already exists");
        }
        userUrls.put(user, userId);
        return ApiResponse.success(user);
    }

    @GetMapping("/all")
    public ApiResponse<Map> all() {
        return ApiResponse.success(userUrls);
    }
}
