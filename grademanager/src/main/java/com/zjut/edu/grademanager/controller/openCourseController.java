package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.Error.BusinessException;
import com.zjut.edu.grademanager.Error.EnumBusinessError;
import com.zjut.edu.grademanager.Model.zhangz_Opencourses15;
import com.zjut.edu.grademanager.Services.OpenCoursesService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("/openCourses")
@RequestMapping("/openCourses")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class openCourseController extends baseController {
    /**
     * @author John
     * @date 2020/7/16 15:56
     */
    @Autowired(required = false)
    OpenCoursesService openCoursesService;

    @RequestMapping(value = "/addOpenCourse", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    @ResponseBody
    public CommonReturnType addOpenCourse(zhangz_Opencourses15 o) throws Exception {
        int status = openCoursesService.insertOpenCourse(o.getZz_Cono15(), o.getZz_Cno15(), o.getZz_Year15(), o.getZz_Term15(), o.getZz_Tno15());
        return CommonReturnType.create(status);
    }

    @RequestMapping(value = "/deleteOpenCourse")
    @ResponseBody
    public CommonReturnType insertCourse(@RequestParam("ono") Integer ono) throws Exception {
        Object status = openCoursesService.deleteOpenCourse(ono);
        int getstatus = Integer.parseInt(status.toString());
        if (getstatus == -1){
            BusinessException businessException = new BusinessException(EnumBusinessError.REJECT_DELETE_OPENCOURSE);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        return CommonReturnType.create(status);
    }

}
