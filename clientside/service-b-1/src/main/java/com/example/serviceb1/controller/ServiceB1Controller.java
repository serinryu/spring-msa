package com.example.serviceb1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serviceB")
public class ServiceB1Controller {
    @GetMapping("/statuscheck")
    public String checkState(){
        return "Service-B: inst002 정상";
    }
}
