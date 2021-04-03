package com.mh.msw.controller;

import com.alibaba.fastjson.JSON;
import com.mh.msw.model.User;
import com.mh.msw.service.UserService;
import com.mh.msw.untils.Log4j2Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public String getAllUser(){
        List<User> userList = userService.getUserList();
        String str= JSON.toJSON(userList).toString();
        Log4j2Util.logger.info("用户列表："+str);
        return str;
    }

    @GetMapping("/addUser")
    public String addUser(){
        User t1 = new User();
        t1.setName("张三");
        t1.setUserName("zhangsan");
        t1.setPassword("123");
        t1.setStatus("Y");
        userService.addUser(t1);
        return "增加用户"+t1.getName();
    }
    //test

    @GetMapping("/updateUser")
    public String updateUser(){
        User t1 = new User();
        t1.setId(2);
        t1.setName("张三");
        t1.setUserName("zhangsan");
        t1.setPassword("456789");
        t1.setStatus("N");
        userService.updateUser(t1);
        return "修改用户"+t1.getId()+"信息";
        //s
    }
}
