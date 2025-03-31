package com.bbblllack;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@EnableAspectJAutoProxy
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class UserApplication implements CommandLineRunner {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(5000L);
        for (ServiceInstance instance : discoveryClient.getInstances(appName)) {
            log.info("检测到当前实例: {}:{}", instance.getHost(), instance.getPort());
        }
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            System.out.println("runner...");
        };
    }
}
