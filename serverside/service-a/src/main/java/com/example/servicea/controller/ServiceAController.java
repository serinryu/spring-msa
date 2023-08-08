package com.example.servicea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/serviceA")
public class ServiceAController {

    @Autowired
    private DiscoveryClient discoveryClient;

    private static final String GATEWAY_NAME = "GATEWAY-SERVICE";

    @GetMapping("/statuscheck")
    public String checkState(){
        return "Service-A: inst001 정상";
    }


    @GetMapping("/test")
    public String callServiceA() throws UnsupportedOperationException, IOException {
        String url = "";
        String apiPath = "/serviceB/statuscheck";
        List<ServiceInstance> instance = discoveryClient.getInstances(GATEWAY_NAME);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response=null;

        url = instance.get(0).getUri().toString(); // 192.168.0.33:gateway-service:8800
        url += apiPath;

        try{
            response=restTemplate.exchange(url, HttpMethod.GET, getHeaders(), String.class);
        }catch (Exception ex){
            System.out.println(ex);
        }

        System.out.println(response.getBody());
        //Service-B: inst001 정상
        //Service-B: inst002 정상

        return "Service-A: inst001 호출" + " > " + response.getBody().toString();
    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

}
