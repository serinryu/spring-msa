package com.example.servicea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/service1")
public class ServiceAController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_B_NAME = "SERVICE-B";


    @GetMapping("/test")

    public String callServiceA() throws UnsupportedOperationException, IOException {
        ResponseEntity<String> res;
        String apiPath = "/service2/statuscheck";
        res = restTemplate.getForEntity("http://" + SERVICE_B_NAME + apiPath, String.class); // Ribbon을 통해 서비스끼리 Eureka에 등록된 서비스명으로 호출이 가능하다.



        return "Service-A: inst001 호출" + " > " + res.getBody().toString();

    }

}