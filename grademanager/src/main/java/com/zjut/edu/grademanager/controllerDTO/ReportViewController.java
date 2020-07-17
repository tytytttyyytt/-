package com.zjut.edu.grademanager.controllerDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Reportview15;
import com.zjut.edu.grademanager.Error.BusinessException;
import com.zjut.edu.grademanager.Error.EnumBusinessError;
import com.zjut.edu.grademanager.ServiceDTO.ReportViewService;
import com.zjut.edu.grademanager.controller.baseController;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/ReportStuView")
@RequestMapping("/ReportStuView")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ReportViewController extends baseController {
    /**
     * @author John
     * @date 2020/7/11 22:54
     */
    @Autowired(required = false)
    ReportViewService reportViewService;
    @Autowired(required = false)
    List<zhangz_Reportview15> zhangz_reportview15ListAll;
    @Autowired(required = false)
    List zhangz_reportview15ListAllByCondition;


    @RequestMapping("/getAllInfo")
    @ResponseBody
    public CommonReturnType getAllInfo() throws Exception {
        zhangz_reportview15ListAll = reportViewService.getAll();
        if (zhangz_reportview15ListAll == null) {
            BusinessException businessException = new BusinessException(EnumBusinessError.REPORT_LOAD_FAILED);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        return CommonReturnType.create(zhangz_reportview15ListAll);
    }

    @RequestMapping("/getInfoByCondition")
    @ResponseBody
    public CommonReturnType getInfoBySno(@RequestParam("sno") String sno, @RequestParam(value = "year",required = false) Integer year, @RequestParam(value = "term",required = false) Integer term) throws Exception {
        zhangz_reportview15ListAllByCondition = reportViewService.getInfoBySno(sno, year, term);
        return CommonReturnType.create(zhangz_reportview15ListAllByCondition);
    }
}
