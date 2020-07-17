package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.DTO.zhangz_Studentoutview15;
import com.zjut.edu.grademanager.Error.BusinessException;
import com.zjut.edu.grademanager.Error.EnumBusinessError;
import com.zjut.edu.grademanager.Model.LoginObject;
import com.zjut.edu.grademanager.Model.zhangz_Admin15;
import com.zjut.edu.grademanager.Model.zhangz_Students15;
import com.zjut.edu.grademanager.Model.zhangz_Teachers15;
import com.zjut.edu.grademanager.ServiceDTO.StudentOutViewService;
import com.zjut.edu.grademanager.Services.AdminService;
import com.zjut.edu.grademanager.Services.StudentsService;
import com.zjut.edu.grademanager.Services.TeachersService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/login")
@RequestMapping("/login")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class loginController extends baseController {
    /**
     * @author John
     * @date 2020/7/12 10:02
     */

    @Autowired(required = false)
    StudentsService studentsService;
    @Autowired(required = false)
    TeachersService teachersService;
    @Autowired(required = false)
    AdminService adminService;
    @Autowired(required = false)
    StudentOutViewService studentOutViewService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    @ResponseBody
    public CommonReturnType login(LoginObject o) throws Exception {
        System.out.println(o);
        zhangz_Students15 stu = studentsService.getStudent(o.getUsername());
        zhangz_Teachers15 tea = teachersService.getTeacher(o.getUsername());
        zhangz_Admin15 admin = adminService.getAdmin(o.getUsername());
        if (stu == null && tea == null && admin == null) {
            BusinessException businessException = new BusinessException(EnumBusinessError.USER_NOT_EXIST);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        if (stu != null) {
            if (stu.getZz_SPwd().equals(o.getPassword())) {
                List<zhangz_Studentoutview15> returnstu = studentOutViewService.getInfoBySno(o.getUsername());
                Map returnmap = new HashMap();
                returnmap.put("role", "student");
                returnmap.put("data", returnstu);
                return CommonReturnType.create(returnmap);
            } else {
                BusinessException businessException = new BusinessException(EnumBusinessError.PASSWORD_WRONG);
                System.out.println(businessException.getErrorMsg());
                throw businessException;
            }
        } else if (tea != null) {
            if (tea.getZz_TPwd().equals(o.getPassword())) {
                Map returnmap = new HashMap();
                returnmap.put("role", "teacher");
                returnmap.put("data", tea);
                return CommonReturnType.create(returnmap);
            } else {
                BusinessException businessException = new BusinessException(EnumBusinessError.PASSWORD_WRONG);
                System.out.println(businessException.getErrorMsg());
                throw businessException;
            }
        } else if (admin != null) {
            if (admin.getZz_Apwd15().equals(o.getPassword())) {
                Map returnmap = new HashMap();
                returnmap.put("role", "admin");
                returnmap.put("data", admin);
                return CommonReturnType.create(returnmap);
            } else {
                BusinessException businessException = new BusinessException(EnumBusinessError.PASSWORD_WRONG);
                System.out.println(businessException.getErrorMsg());
                throw businessException;
            }
        }
        return CommonReturnType.create("已接受数据");
    }
}
