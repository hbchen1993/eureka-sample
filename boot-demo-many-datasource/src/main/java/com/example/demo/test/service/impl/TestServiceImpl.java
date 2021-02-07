package com.example.demo.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.Reader;
import com.example.demo.config.Write;
import com.example.demo.test.dao.TestDao;
import com.example.demo.test.entity.User;
import com.example.demo.test.service.TestService;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Reader
    @Override
    public void reader() {
        List<User> list = testDao.reader();
        System.out.println(list.size());
    }

    @Write
    @Override
    public void write() {
        testDao.write();
    }

}
