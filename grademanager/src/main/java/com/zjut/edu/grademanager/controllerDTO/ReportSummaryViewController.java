package com.zjut.edu.grademanager.controllerDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Reportsummaryview15;
import com.zjut.edu.grademanager.Error.BusinessException;
import com.zjut.edu.grademanager.Error.EnumBusinessError;
import com.zjut.edu.grademanager.ServiceDTO.ReportSummaryViewService;
import com.zjut.edu.grademanager.controller.baseController;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/ReportView")
@RequestMapping("/ReportView")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ReportSummaryViewController extends baseController {
    /**
     * @author John
     * @date 2020/7/11 22:54
     */
    @Autowired(required = false)
    ReportSummaryViewService reportSummaryViewService;
    @Autowired(required = false)
    List<zhangz_Reportsummaryview15> zhangz_reportsummaryview15ListAll;
    @Autowired(required = false)
    List<zhangz_Reportsummaryview15> zhangz_reportsummaryview15ListAllByClasses;

    @RequestMapping("/getAllInfo")
    @ResponseBody
    public CommonReturnType getAllInfo() throws Exception {
        zhangz_reportsummaryview15ListAll = reportSummaryViewService.getAll();
        if (zhangz_reportsummaryview15ListAll == null) {
            BusinessException businessException = new BusinessException(EnumBusinessError.REPORT_LOAD_FAILED);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        return CommonReturnType.create(zhangz_reportsummaryview15ListAll);
    }

    @RequestMapping("/getInfoBySno")
    @ResponseBody
    public CommonReturnType getInfoBySno(@RequestParam("sno") String sno) throws Exception {
        zhangz_reportsummaryview15ListAllByClasses = reportSummaryViewService.getInfoBySno(sno);
        return CommonReturnType.create(zhangz_reportsummaryview15ListAllByClasses);
    }


}
