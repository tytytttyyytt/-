package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.Error.BusinessException;
import com.zjut.edu.grademanager.Error.EnumBusinessError;
import com.zjut.edu.grademanager.Services.ReportService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/report")
@RequestMapping("/report")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class reportController extends baseController {
    /**
     * @author John
     * @date 2020/7/15 1:14
     */
    @Autowired(required = false)
    ReportService reportService;

    @RequestMapping("/updateOrInsertGrade")
    @ResponseBody
    public CommonReturnType getAllProfession(@RequestParam("ono") Integer ono, @RequestParam("sno") String sno, @RequestParam("grade") Integer grade) throws Exception {
        Object status = reportService.insertGrade(ono, sno, grade);
        int getstatus = Integer.parseInt(status.toString());
        if (getstatus == -1){
            BusinessException businessException = new BusinessException(EnumBusinessError.INSERT_OR_UPDATE_FAILED);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        if (getstatus == -2){
            BusinessException businessException = new BusinessException(EnumBusinessError.INSERT_FAILED_EMPTY);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        return CommonReturnType.create(status);
    }
}
