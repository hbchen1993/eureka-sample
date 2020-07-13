package com.example.demo.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import net.spy.memcached.MemcachedClient;

@Component
public class MemcachedPool {

    @Autowired
    private MemcacheSource memcacheSource;

    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        return new MemcachedClient(new InetSocketAddress(memcacheSource.getIp(), memcacheSource.getPort()));
    }

}
