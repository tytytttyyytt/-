package com.zjut.edu.grademanager.controllerDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Studentsview15;
import com.zjut.edu.grademanager.Error.BusinessException;
import com.zjut.edu.grademanager.Error.EnumBusinessError;
import com.zjut.edu.grademanager.ServiceDTO.StudentViewService;
import com.zjut.edu.grademanager.controller.baseController;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/StuView")
@RequestMapping("/StuView")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class StudentViewController extends baseController {
    /**
     * @author John
     * @date 2020/7/15 16:13
     */
    @Autowired(required = false)
    StudentViewService studentViewService;
    @Autowired(required = false)
    List<zhangz_Studentsview15> zhangz_Studentsview15ListAll;

    @RequestMapping("/getAllInfo")
    @ResponseBody
    public CommonReturnType getAllInfo() throws Exception {
        zhangz_Studentsview15ListAll = studentViewService.getAllStudent();
        if (zhangz_Studentsview15ListAll == null) {
            BusinessException businessException = new BusinessException(EnumBusinessError.STUDENT_LIST_LOAD_FAILED);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        return CommonReturnType.create(zhangz_Studentsview15ListAll);
    }
}
