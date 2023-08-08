package com.example.servicea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAApplication.class, args);
    }

    @Bean
    @LoadBalanced // @LoadBalenced는 Eureka에 내장된 로드밸런서인 Ribbon을 이용해 라운드 로빈을 기반으로 서비스 인스턴스를 호출할 수 있다.
    public RestTemplate getRestTemplate(){
        return new RestTemplate(); // 간편하게 Rest 방식 API를 호출할 수 있는 Spring 내장 클래스
    }
}
