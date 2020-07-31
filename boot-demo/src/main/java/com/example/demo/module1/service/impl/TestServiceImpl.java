package com.example.demo.module1.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.module1.dao.TableBMapper;
import com.example.demo.module1.entity.TableB;
import com.example.demo.module1.service.TestService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * <Description> <br>
 * 框架初测后台接口业务层逻辑接口实现
 * 
 * @author xxx<br>
 * @version 1.0<br>
 * @CreateDate 2020-7-27 <br>
 * @see com.example.demo.module1.service.impl <br>
 */
@Service
public class TestServiceImpl implements TestService {

    private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TableBMapper tableBMapper;

    @Override
    public Page<TableB> testMethod() {

        redisTemplate.opsForValue().set("test:test1", "测试");
        logger.info(redisTemplate.opsForValue().get("test:test1").toString());

        PageHelper.startPage(1, 2);
        Page<TableB> page = tableBMapper.qryTableBDatas();

        return page;
    }

}
