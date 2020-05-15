package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("eureka-client1")
public interface HelloService {

    @RequestMapping("/test/{name}")
    String hello(@PathVariable("name") String name);

}
