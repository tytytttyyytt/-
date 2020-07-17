package com.zjut.edu.grademanager.controllerDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Opencoursesview15;
import com.zjut.edu.grademanager.Error.BusinessException;
import com.zjut.edu.grademanager.Error.EnumBusinessError;
import com.zjut.edu.grademanager.ServiceDTO.OpenCoursesViewService;
import com.zjut.edu.grademanager.controller.baseController;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/OpenCoursesStuView")
@RequestMapping("/OpenCoursesStuView")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class OpenCoursesViewController extends baseController {
    /**
     * @author John
     * @date 2020/7/11 22:54
     */
    @Autowired(required = false)
    OpenCoursesViewService openCoursesViewService;
    @Autowired(required = false)
    List<zhangz_Opencoursesview15> zhangz_opencoursesview15ListAll;
    @Autowired(required = false)
    List<zhangz_Opencoursesview15> zhangz_opencoursesview15ListByYearAndTerm;
    @Autowired(required = false)
    List<zhangz_Opencoursesview15> zhangz_opencoursesview15ListByCono;
    @Autowired(required = false)
    List zhangz_opencoursesview15ListAllByCondition;


    @RequestMapping("/getAllInfo")
    @ResponseBody
    public CommonReturnType getAllInfo() throws Exception {
        zhangz_opencoursesview15ListAll = openCoursesViewService.getAll();
        if (zhangz_opencoursesview15ListAll == null) {
            BusinessException businessException = new BusinessException(EnumBusinessError.REPORT_LOAD_FAILED);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        return CommonReturnType.create(zhangz_opencoursesview15ListAll);
    }

    @RequestMapping("/getInfoByCondition")
    @ResponseBody
    public CommonReturnType getInfoBySno(@RequestParam(value = "cono", required = false) String cono, @RequestParam(value = "coname", required = false) String coname, @RequestParam(value = "year", required = false) Integer year, @RequestParam(value = "term", required = false) Integer term, @RequestParam(value = "ways", required = false) Integer ways, @RequestParam(value = "tname", required = false) String tname, @RequestParam(value = "pno", required = false) Integer pno, @RequestParam(value = "cyear", required = false) Integer cyear, @RequestParam(value = "cno", required = false) Integer cno) throws Exception {
        zhangz_opencoursesview15ListAllByCondition = openCoursesViewService.getInfoBySno(cono, coname, year, term, ways, tname, pno, cyear, cno);
        return CommonReturnType.create(zhangz_opencoursesview15ListAllByCondition);
    }

    @RequestMapping("/getInfoByYearOrTerm")
    @ResponseBody
    public CommonReturnType getInfoBySno(@RequestParam("tno") String tno, @RequestParam(value = "year", required = false) Integer year, @RequestParam(value = "term", required = false) Integer term) throws Exception {
        System.out.println("tno:" + tno + "year:" + year + "term:" + term);
        if (year == null && term == null) {
            zhangz_opencoursesview15ListByYearAndTerm = openCoursesViewService.getByTno(tno);
        } else if (year == null) {
            zhangz_opencoursesview15ListByYearAndTerm = openCoursesViewService.getByTerm(tno, term);
        } else if (term == null) {
            zhangz_opencoursesview15ListByYearAndTerm = openCoursesViewService.getByYear(tno, year);
        } else {
            zhangz_opencoursesview15ListByYearAndTerm = openCoursesViewService.getByYearAndTerm(tno, year, term);
        }
        return CommonReturnType.create(zhangz_opencoursesview15ListByYearAndTerm);
    }

    @RequestMapping("/getInfoByCono")
    @ResponseBody
    public CommonReturnType getInfoByCono(@RequestParam("cono") Integer cono) throws Exception {
        zhangz_opencoursesview15ListByCono = openCoursesViewService.getCnoByCono(cono);
        return CommonReturnType.create(zhangz_opencoursesview15ListByCono);
    }
}
