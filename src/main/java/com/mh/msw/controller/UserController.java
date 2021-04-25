package com.mh.msw.controller;

import com.alibaba.fastjson.JSON;
import com.mh.msw.model.User;
import com.mh.msw.service.UserService;
import com.mh.msw.untils.Log4j2Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String addUser(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password,@RequestParam(value = "name") String name){
        User new_user = new User();
        User cheak_user = new User();
        cheak_user.setUserName(username);
        User old_user = userService.findUserByUsername(cheak_user);
        if(old_user!=null){
            return username + "用户已存在";
        }
        new_user.setUserName(username);
        new_user.setName(name);
        new_user.setPassword(password);
        new_user.setStatus("Y");
        userService.addUser(new_user);
        return "注册用户信息： " +  JSON.toJSON(new_user).toString();
    }

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

//    @GetMapping("/login")
//    public String cheakUser(){
//        User t1 = new User();
//        t1.setId(2);
//        t1.setName("张三");
//        t1.setUserName("zhangsan");
//        t1.setPassword("456789");
//        t1.setStatus("N");
//        userService.updateUser(t1);
//        return "修改用户"+t1.getId()+"信息";
//        //s
//    }

    @PostMapping("/login")
    public String login(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password){
        User u1 = new User();
        u1.setUserName(username);
        Log4j2Util.logger.info("u1："+u1.toString());
        User u2 = userService.findUserByUsername(u1);
        u2.setUserName(username);
        Log4j2Util.logger.info("u2："+u2.toString());
        if(username==null){
            return "无此账户";
        }else if(password.equals(u2.getPassword()))
        {
            String str= JSON.toJSON(u2).toString();
            return "登录成功! 用户信息 ： " + str;
        }else {
            return "密码错误";
        }

    }

}
