package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.Model.ChangePasswordObject;
import com.zjut.edu.grademanager.Model.zhangz_Teachers15;
import com.zjut.edu.grademanager.Services.TeachersService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/teacher")
@RequestMapping("/teacher")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class teacherController extends baseController {
    /**
     * @author John
     * @date 2020/7/11 20:37
     */

    @Autowired(required = false)
    TeachersService teachersService;
    @Autowired(required = false)
    List<zhangz_Teachers15> zhangz_teachers15List;

    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    @ResponseBody
    public CommonReturnType updatepassword(ChangePasswordObject o) throws Exception {
        int status = teachersService.updateTeacherPassword(o.getPassword(), o.getNo());
        return CommonReturnType.create(status);
    }

    @RequestMapping("/getAllTeacher")
    @ResponseBody
    public CommonReturnType getAllTeacher() throws Exception {
        zhangz_teachers15List = teachersService.getAllTeacher();
        return CommonReturnType.create(zhangz_teachers15List);
    }

    @RequestMapping(value = "/updateTeacherInfo", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    @ResponseBody
    public CommonReturnType updatepassword(zhangz_Teachers15 o) throws Exception {
        int status = teachersService.updateTeacherByTno(o.getZz_Name15(), o.getZz_Sex15(), o.getZz_Age15(), o.getZz_Level15(), o.getZz_Phone15(), o.getZz_TPwd(), o.getZz_Tno15());
        return CommonReturnType.create(status);
    }

    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    @ResponseBody
    public CommonReturnType addTeacher(zhangz_Teachers15 o) throws Exception {
        int status = teachersService.addTeacher(o.getZz_Tno15(), o.getZz_Name15(), o.getZz_Sex15(), o.getZz_Age15(), o.getZz_Level15(), o.getZz_Phone15(), o.getZz_TPwd());
        return CommonReturnType.create(status);
    }

    @RequestMapping("/eraseTeacherInfo")
    @ResponseBody
    public CommonReturnType eraseTeacherInfo(@RequestParam("tno") String tno) throws Exception {
        int status = teachersService.eraseTeacherInfo(tno);
        return CommonReturnType.create(status);
    }
}
