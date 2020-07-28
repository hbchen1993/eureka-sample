package com.example.demo.module1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.module1.entity.TableB;
import com.example.demo.module1.service.TestService;
import com.github.pagehelper.Page;

/**
 * <Description> <br>
 * 框架初测后台接口分发控制器
 * 
 * @author xxx<br>
 * @version 1.0<br>
 * @CreateDate 2020-7-27 <br>
 * @see com.example.demo.module1.controller <br>
 */
@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * Description: <br>
     * 测试方法
     * 
     * @author xxx<br>
     * @param param
     * @return <br>
     */
    @RequestMapping("testMethod")
    @ResponseBody
    public Map<String, Object> testMethod(String param) {
        Map<String, Object> result = new HashMap<String, Object>();
        Page<TableB> page = testService.testMethod();
        result.put("list", page);
        result.put("tatal", page.getTotal());
        return result;
    }

}
