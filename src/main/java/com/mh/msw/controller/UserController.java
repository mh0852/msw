package com.mh.msw.controller;

import com.alibaba.fastjson.JSON;
import com.mh.msw.model.User;
import com.mh.msw.service.UserService;
import com.mh.msw.untils.Log4j2Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;


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
    public String login(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password,HttpServletRequest request){
        User u1 = new User();
        u1.setUserName(username);
        Log4j2Util.logger.info("u1："+u1.toString());
        User u2 = userService.findUserByUsername(u1);
        if(u2==null||username.equals("用户名")){
            return "redirect:acct_null.html";
        }else if(password.equals(u2.getPassword()))
        {
            Log4j2Util.logger.info("u2："+u2.toString());
            String str= JSON.toJSON(u2).toString();
            HttpSession session = request.getSession();
            session.setAttribute("username", u2.getUserName());
            session.setAttribute("session_id",session.getId());
           // redisTemplate.opsForValue().set("loginUser:" + u2.getUserName(), session.getId());
            return "redirect:console.html";
        }else {
            Log4j2Util.logger.info("u2："+u2.toString());
            return "redirect:passwd_error.html";
        }



    }

}
