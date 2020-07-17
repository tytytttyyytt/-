package com.zjut.edu.grademanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class helloController extends baseController {
    /**
     * @author John
     * @date 2020/7/11 19:40
     */
    @RequestMapping("/index")
    public String sayHello() {
        return "index";
    }

}
