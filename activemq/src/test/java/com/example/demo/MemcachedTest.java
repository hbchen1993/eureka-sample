package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.spy.memcached.MemcachedClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcachedTest {

    @Autowired
    private MemcachedClient memcachedClient;

    @Test
    public void test() {
        memcachedClient.set("test_key", 1000, "test_value");
        System.out.println(memcachedClient.get("test_key"));
    }

}
