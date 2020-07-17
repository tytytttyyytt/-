package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.Model.ChangePasswordObject;
import com.zjut.edu.grademanager.Services.AdminService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/admin")
@RequestMapping("/admin")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class adminController extends baseController {
    /**
     * @author John
     * @date 2020/7/11 20:37
     */

    @Autowired(required = false)
    AdminService adminService;


    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    @ResponseBody
    public CommonReturnType updatepassword(ChangePasswordObject o) throws Exception {
        int status = adminService.updateAdminPassword(o.getPassword(), o.getNo());
        return CommonReturnType.create(status);
    }

}
