package com.bbblllack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MyEnumConverter myEnumConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(myEnumConverter);
    }

    @LoadBalanced
    @Qualifier("lb")
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Qualifier("nlb")
    public RestTemplate nlbRestTemplate() {
        return new RestTemplate();
    }
}
