package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.Services.XClassesService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/xclass")
@RequestMapping("/xclass")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class xClassesController extends baseController {
    /**
     * @author John
     * @date 2020/7/14 12:05
     */

    @Autowired(required = false)
    XClassesService xClassesService;
    @Autowired(required = false)
    List zhangz_XClasses15List;
    @Autowired(required = false)
    List zhangz_XClasses15ListClass;

    @RequestMapping("/getAllYears")
    @ResponseBody
    public CommonReturnType getAllProfession(@RequestParam("pno") Integer pno) {
        zhangz_XClasses15List = xClassesService.getAllYears(pno);
        return CommonReturnType.create(zhangz_XClasses15List);
    }

    @RequestMapping("/getAllClasses")
    @ResponseBody
    public CommonReturnType getAllClass(@RequestParam("pno") Integer pno, @RequestParam("year") Integer year) {
        zhangz_XClasses15ListClass = xClassesService.getAllClasses(pno, year);
        return CommonReturnType.create(zhangz_XClasses15ListClass);
    }
}
