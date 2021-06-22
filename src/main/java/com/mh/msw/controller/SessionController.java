package com.mh.msw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class SessionController {
    @RequestMapping(value = "/get-session-id.action",method = RequestMethod.GET)
    public String getSessionId(){
        System.out.println("到session控制器");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String sessionId="";
        Object temp=session.getAttribute("test");//session中存储userCode的key为USER
        System.out.println(temp.toString());
        sessionId=session.getId();
        return sessionId;
    }

    @RequestMapping("/setsession")
    @ResponseBody
    String home(HttpSession session) {
        session.setAttribute("test", "hahah");
        return "Hello World!";
    }

}

