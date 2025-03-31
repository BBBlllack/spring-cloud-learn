package com.bbblllack.config;

import com.bbblllack.filter.AuthorizationFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalFilterConfig {

    @Bean
    public AuthorizationFilter authorizationFilter() {
        return new AuthorizationFilter();
    }
}
