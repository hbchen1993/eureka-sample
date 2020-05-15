package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @RequestMapping("test/{name}")
    @ResponseBody
    public Map<String, Object> test(@PathVariable("name") String name) {
        System.out.println(name);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("SUCCESS", true);
        result.put("MSG", "服务提供者！");
        result.put("NAME", name);
        return result;
    }

}
