package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.Error.BusinessException;
import com.zjut.edu.grademanager.Error.EnumBusinessError;
import com.zjut.edu.grademanager.Model.zhangz_Courses15;
import com.zjut.edu.grademanager.Services.CoursesService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/courses")
@RequestMapping("/courses")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class coursesController extends baseController {
    /**
     * @author John
     * @date 2020/7/15 22:30
     */
    @Autowired(required = false)
    CoursesService coursesService;
    @Autowired(required = false)
    List<zhangz_Courses15> zhangz_courses15List;

    @RequestMapping("/getAllCourses")
    @ResponseBody
    public CommonReturnType getAllCourses() {
        zhangz_courses15List = coursesService.getAllCourses();
        return CommonReturnType.create(zhangz_courses15List);
    }

    @RequestMapping(value = "/updateCourse", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    @ResponseBody
    public CommonReturnType updateCourse(zhangz_Courses15 o) throws Exception {
        int status = coursesService.updateCourse(o.getZz_Name15(), o.getZz_Period15(), o.getZz_Credit15(), o.getZz_Way15(), o.getZz_Cono15());
        return CommonReturnType.create(status);
    }

    @RequestMapping(value = "/insertCourse", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    @ResponseBody
    public CommonReturnType insertCourse(zhangz_Courses15 o) throws Exception {
        int status = coursesService.insertCourse(o.getZz_Name15(), o.getZz_Period15(), o.getZz_Way15(), o.getZz_Credit15());
        return CommonReturnType.create(status);
    }

    @RequestMapping(value = "/deleteCourse")
    @ResponseBody
    public CommonReturnType insertCourse(@RequestParam("cono") Integer cono) throws Exception {
        Object status = coursesService.deleteCourse(cono);
        int getstatus = Integer.parseInt(status.toString());
        if (getstatus == -1){
            BusinessException businessException = new BusinessException(EnumBusinessError.REJECT_DELETE_COURSE);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        return CommonReturnType.create(status);
    }
}
