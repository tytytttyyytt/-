package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.Services.ProfessionService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/profession")
@RequestMapping("/profession")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class professionsController extends baseController {
    /**
     * @author John
     * @date 2020/7/14 12:05
     */

    @Autowired(required = false)
    ProfessionService professionService;
    @Autowired(required = false)
    List zhangz_Professions15List;

    @RequestMapping("/getAllProfession")
    @ResponseBody
    public CommonReturnType getAllProfession() {
        zhangz_Professions15List = professionService.getAllProfession();
        return CommonReturnType.create(zhangz_Professions15List);
    }
}
