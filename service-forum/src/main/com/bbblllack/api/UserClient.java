package com.bbblllack.api;

import com.bbblllack.model.common.ApiResponse;
import com.bbblllack.model.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("service-user")
public interface UserClient {

    @GetMapping("/user/all/{id}")
    public ApiResponse<User> getUsersAllInfo(@RequestHeader(value = "Token", required = false) String token,
                                             @PathVariable("id") Integer id);
}
