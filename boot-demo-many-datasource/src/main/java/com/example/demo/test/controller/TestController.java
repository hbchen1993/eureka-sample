package com.example.demo.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.test.service.TestService;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("write")
    @ResponseBody
    public void write() {
        testService.write();
    }

    @RequestMapping("reader")
    @ResponseBody
    public void reader() {
        testService.reader();
    }

}
