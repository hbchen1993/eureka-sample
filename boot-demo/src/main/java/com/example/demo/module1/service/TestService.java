package com.example.demo.module1.service;

import org.springframework.stereotype.Service;

import com.example.demo.module1.entity.TableB;
import com.github.pagehelper.Page;

/**
 * <Description> <br>
 * 框架初测后台接口业务层逻辑接口定义
 * 
 * @author chen.haibo<br>
 * @version 1.0<br>
 * @CreateDate 2020-7-27 <br>
 * @see com.example.demo.module1.service <br>
 */
@Service
public interface TestService {

    /**
     * Description: <br>
     * 测试方法
     * 
     * @author xxx<br>
     * @return <br>
     */
    Page<TableB> testMethod();

}
