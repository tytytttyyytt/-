package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.Model.zhangz_Provinces15;
import com.zjut.edu.grademanager.Services.ProvincesService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/provinces")
@RequestMapping("/provinces")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class provincesController {
    /**
     * @author John
     * @date 2020/7/15 16:53
     */
    @Autowired(required = false)
    ProvincesService provincesService;
    @Autowired(required = false)
    List<zhangz_Provinces15> zhangz_provinces15List;

    @RequestMapping("/getAllProvinces")
    @ResponseBody
    public CommonReturnType getAllProfession() {
        zhangz_provinces15List = provincesService.getAllProvinces();
        return CommonReturnType.create(zhangz_provinces15List);
    }
}
