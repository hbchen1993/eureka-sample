package com.example.demo.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class Consumer1 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "test")
    public void receiveQueue(Map<String, Object> map) {
        System.out.println(map.get("MSG"));
        logger.info("发送消息:" + JSON.toJSONString(map));
    }

}
