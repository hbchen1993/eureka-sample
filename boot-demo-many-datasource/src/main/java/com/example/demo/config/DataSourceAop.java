package com.example.demo.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("@annotation(com.example.demo.config.Reader)")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.example.demo.config.Write)")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.reader();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.write();
    }
}
