package com.example.serviceb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serviceB")
public class ServiceBController {
    @GetMapping("/statuscheck")
    public String checkState(){
        return "Service-B: inst001 정상";
    }
}
