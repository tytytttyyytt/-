package com.zjut.edu.grademanager.controllerDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Studentoutview15;
import com.zjut.edu.grademanager.Error.BusinessException;
import com.zjut.edu.grademanager.Error.EnumBusinessError;
import com.zjut.edu.grademanager.ServiceDTO.StudentOutViewService;
import com.zjut.edu.grademanager.controller.baseController;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/StuOutView")
@RequestMapping("/StuOutView")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class StudentOutViewController extends baseController {
    /**
     * @author John
     * @date 2020/7/11 22:54
     */
    @Autowired(required = false)
    StudentOutViewService studentOutViewService;
    @Autowired(required = false)
    List<zhangz_Studentoutview15> zhangz_Studentoutview15ListAll;
    @Autowired(required = false)
    List<zhangz_Studentoutview15> zhangz_Studentoutview15BySno;

    @RequestMapping("/getAllInfo")
    @ResponseBody
    public CommonReturnType getAllInfo() throws Exception {
        zhangz_Studentoutview15ListAll = studentOutViewService.getAll();
        if (zhangz_Studentoutview15ListAll == null) {
            BusinessException businessException = new BusinessException(EnumBusinessError.REPORT_LOAD_FAILED);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        return CommonReturnType.create(zhangz_Studentoutview15ListAll);
    }

    @RequestMapping("/getInfoBySno")
    @ResponseBody
    public CommonReturnType getCitiesNum(@RequestParam("sno") String sno) throws Exception {
        zhangz_Studentoutview15BySno = studentOutViewService.getInfoBySno(sno);
        return CommonReturnType.create(zhangz_Studentoutview15BySno);
    }
}
