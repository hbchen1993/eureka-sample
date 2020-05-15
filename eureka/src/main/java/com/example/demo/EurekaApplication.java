package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

    public static void main(String[] args) {
        System.out.println("新的分支");
        System.out.println("主分支修改");
        System.out.println("新分支又修改了");
        System.out.println("新分支又又修改了");

        System.out.println("新分支又又修改了1");
        
        System.out.println("新分支又又修改了2");
        SpringApplication.run(EurekaApplication.class, args);
    }

}
