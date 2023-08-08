package com.example.serviceb1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceB1Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceB1Application.class, args);
    }

}
