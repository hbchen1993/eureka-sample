package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.Producer;

import net.spy.memcached.MemcachedClient;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private Producer producer;

    @Autowired
    private MemcachedClient memcachedClient;

    @RequestMapping("test")
    @ResponseBody
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("SUCCESS", true);
        map.put("MSG", "测试");

        // Destination destination = new ActiveMQQueue("test");

        // producer.sendMessage(destination, map);

        memcachedClient.set("test_key1", 1000, "测试汉字");
        System.out.println(memcachedClient.get("test_key1"));

        return map;
    }

}
