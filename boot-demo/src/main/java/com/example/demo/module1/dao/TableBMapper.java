package com.example.demo.module1.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.module1.entity.TableB;
import com.github.pagehelper.Page;

/**
 * <Description> <br>
 * 测试表数据库持久层
 * 
 * @author xxx<br>
 * @version 1.0<br>
 * @CreateDate 2020-7-27 <br>
 * @see com.example.demo.module1.dao <br>
 */
@Mapper
public interface TableBMapper {

    /**
     * Description: <br>
     * 测试查询表数据
     * 
     * @author xxx<br>
     * @return <br>
     */
    Page<TableB> qryTableBDatas();

}
