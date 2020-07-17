package com.zjut.edu.grademanager.controllerDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Coursesummaryview15;
import com.zjut.edu.grademanager.ServiceDTO.CourseSummaryViewService;
import com.zjut.edu.grademanager.controller.baseController;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/CourseSummaryView")
@RequestMapping("/CourseSummaryView")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class CourseSummaryViewController extends baseController {
    /**
     * @author John
     * @date 2020/7/15 11:37
     */
    @Autowired(required = false)
    CourseSummaryViewService courseSummaryViewService;
    @Autowired(required = false)
    List<zhangz_Coursesummaryview15> zhangz_coursesummaryview15List;

    @RequestMapping("/getInfo")
    @ResponseBody
    public CommonReturnType getInfo(@RequestParam("tno") String tno, @RequestParam(value = "year", required = false) Integer year, @RequestParam(value = "term", required = false) Integer term) throws Exception {
        if (year == null && term == null) {
            zhangz_coursesummaryview15List = courseSummaryViewService.getAllCourseSummary(tno);
        } else if (year == null) {
            zhangz_coursesummaryview15List = courseSummaryViewService.getAllCourseSummaryByTerm(tno, term);
        } else if (term == null) {
            zhangz_coursesummaryview15List = courseSummaryViewService.getAllCourseSummaryByYear(tno, year);
        } else {
            zhangz_coursesummaryview15List = courseSummaryViewService.getAllCourseSummaryByYearAndTerm(tno, year, term);
        }
        return CommonReturnType.create(zhangz_coursesummaryview15List);
    }
}
