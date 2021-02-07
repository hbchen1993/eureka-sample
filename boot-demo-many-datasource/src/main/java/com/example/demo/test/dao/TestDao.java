package com.example.demo.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.test.entity.User;

/**
 * <Description> <br>
 * 测试读写分离数据库持久层
 * 
 * @author chen.haibo<br>
 * @version 1.0<br>
 * @CreateDate 2020-11-19 <br>
 * @see com.example.demo.test.dao <br>
 */
@Mapper
public interface TestDao {

    /**
     * Description: <br>
     * 读
     * 
     * @author chen.haibo<br>
     *         <br>
     */
    void write();

    /**
     * Description: <br>
     * 写
     * 
     * @author chen.haibo<br>
     *         <br>
     */
    List<User> reader();

}
