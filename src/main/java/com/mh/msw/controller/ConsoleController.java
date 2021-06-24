package com.mh.msw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class ConsoleController {

    public String redirct(){
        System.out.println("调用redirct");
        return "console";
    }

}
