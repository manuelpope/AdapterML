package com.mlAdapter.mlAdapter.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("Consumer")
public class ControllerApiConsumer {


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot! Consumer";
    }
}
