package com.example.demo.config.shiro;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.exception.SerializationException;
import org.crazycake.shiro.serializer.RedisSerializer;
import org.crazycake.shiro.serializer.StringSerializer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisManager implements IRedisManager {

    @Resource
    private RedisTemplate<String, byte[]> redisTemplate;

    private RedisSerializer<String> keySerializer = new StringSerializer();

    @Override
    public byte[] get(byte[] key) {
        try {
            return redisTemplate.opsForValue().get(keySerializer.deserialize(key));
        }
        catch (SerializationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        try {
            redisTemplate.opsForValue().set(keySerializer.deserialize(key), value, Duration.ofSeconds(expire));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return value;
    }

    @Override
    public void del(byte[] key) {
        try {
            redisTemplate.delete(keySerializer.deserialize(key));
        }
        catch (SerializationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long dbSize(byte[] pattern) {
        try {
            Set<String> results;
            results = redisTemplate.keys(keySerializer.deserialize(pattern));
            return Long.valueOf(results.size());
        }
        catch (SerializationException e) {
            e.printStackTrace();
            return 0l;
        }

    }

    @Override
    public Set<byte[]> keys(byte[] pattern) {
        try {
            Set<byte[]> results = new HashSet<byte[]>();
            Set<String> sets = redisTemplate.keys(keySerializer.deserialize(pattern));
            for (String string : sets) {
                results.add(keySerializer.serialize(string));
            }
            return results;
        }
        catch (SerializationException e) {
            e.printStackTrace();
            return null;
        }
    }

}
