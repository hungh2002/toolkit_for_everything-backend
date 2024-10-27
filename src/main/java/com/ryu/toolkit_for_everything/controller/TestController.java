package com.ryu.toolkit_for_everything.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TestController {
    @GetMapping("/")
    public String testConnection() {
        return "Connection to the Spring Boot application is successful! Hello, World! :)";
    }

}
