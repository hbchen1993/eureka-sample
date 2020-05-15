package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.HelloService;

@RestController
@Configuration
public class InvokerController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/invoker-hello/{name}")
    public String hello(@PathVariable String name) {
        return helloService.hello(name);
    }
}