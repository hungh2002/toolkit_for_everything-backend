package com.ryu.toolkit_for_everything.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin
public class TestController {
    @GetMapping("/test-connection")
    public String testConnection() {
        return "Connection to the Spring Boot application is successful! Hello, World! :)";
    }

}
