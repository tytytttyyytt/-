package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.Error.BusinessException;
import com.zjut.edu.grademanager.Error.EnumBusinessError;
import com.zjut.edu.grademanager.Services.TermAndYearService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/termAndYear")
@RequestMapping("/termAndYear")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class termAndYearController extends baseController {
    /**
     * @author John
     * @date 2020/7/11 20:37
     */

    @Autowired(required = false)
    TermAndYearService termAndYearService;
    @Autowired(required = false)
    List termList;
    @Autowired(required = false)
    List yearList;

    @RequestMapping("/term")
    @ResponseBody
    public CommonReturnType getAllTerms() throws Exception {
        termList = termAndYearService.findAllTerms();
        if (termList == null) {
            BusinessException businessException = new BusinessException(EnumBusinessError.TERM_LOAD_ERROR);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        return CommonReturnType.create(termList);
    }

    @RequestMapping("/year")
    @ResponseBody
    public CommonReturnType getAllYears() throws Exception {
        yearList = termAndYearService.findAllYears();
        if (yearList == null) {
            BusinessException businessException = new BusinessException(EnumBusinessError.YEARS_LOAD_FAILED);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        return CommonReturnType.create(yearList);
    }

}
