package com.example.demo.test.service;

import org.springframework.stereotype.Service;

/**
 * <Description> <br>
 * 测试读写
 * 
 * @author chen.haibo<br>
 * @version 1.0<br>
 * @CreateDate 2020-11-19 <br>
 * @see com.example.demo.test.service <br>
 */
@Service
public interface TestService {

    /**
     * Description: <br>
     * 写
     * 
     * @author chen.haibo<br>
     *         <br>
     */
    void write();

    /**
     * Description: <br>
     * 读
     * 
     * @author chen.haibo<br>
     *         <br>
     */
    void reader();

}
