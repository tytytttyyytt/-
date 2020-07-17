package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.Model.ChangePasswordObject;
import com.zjut.edu.grademanager.Model.zhangz_Students15;
import com.zjut.edu.grademanager.ServiceDTO.ReportViewService;
import com.zjut.edu.grademanager.Services.StudentsService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/student")
@RequestMapping("/student")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class studentController extends baseController {
    /**
     * @author John
     * @date 2020/7/11 20:37
     */

    @Autowired(required = false)
    StudentsService studentsService;
    @Autowired(required = false)
    ReportViewService reportViewService;
    @Autowired(required = false)
    List<zhangz_Students15> zhangz_students15List;
    @Autowired(required = false)
    List<zhangz_Students15> zhangz_students15ListReport;
    @Autowired(required = false)
    List<zhangz_Students15> zhangz_students15ListList;
    @Autowired(required = false)
    Map zhangz_students15Listreturn = new HashMap();

    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    @ResponseBody
    public CommonReturnType updatepassword(ChangePasswordObject o) throws Exception {
        int status = studentsService.updateStudentPassword(o.getPassword(), o.getNo());
        return CommonReturnType.create(status);
    }

    @RequestMapping(value = "/updateStudentInfo", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    @ResponseBody
    public CommonReturnType updatepassword(zhangz_Students15 o) throws Exception {
        int status = studentsService.updateStudentByZz_Sno15(o.getZz_Name15(), o.getZz_Sex15(), o.getZz_Age15(), o.getZz_Cno15(), o.getZz_Cino15(), o.getZz_SPwd(), o.getZz_Sno15());
        return CommonReturnType.create(status);
    }

    @RequestMapping("/getAllByCnoAndCono")
    @ResponseBody
    public CommonReturnType getAllByCno(@RequestParam("cno") Integer cno, @RequestParam("cono") Integer cono) {
        zhangz_students15List = studentsService.getStudentByCno(cno);
        zhangz_students15ListReport = reportViewService.getInfoByCno(cno, cono);
        zhangz_students15Listreturn.put("ori", zhangz_students15List);
        zhangz_students15Listreturn.put("grade", zhangz_students15ListReport);
        return CommonReturnType.create(zhangz_students15Listreturn);
    }

    @RequestMapping("/deleteStudentBySno")
    @ResponseBody
    public CommonReturnType getAllBySno(@RequestParam("sno") String sno) throws Exception {
        zhangz_students15ListList = studentsService.deleteStudentByZz_Sno15(sno);
        return CommonReturnType.create(zhangz_students15ListList);
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    @ResponseBody
    public CommonReturnType addStudent(zhangz_Students15 o) throws Exception {
        int status = studentsService.addStudent(o.getZz_Sno15(), o.getZz_Name15(), o.getZz_Sex15(), o.getZz_Age15(), o.getZz_Totalcredit15(), o.getZz_Cno15(), o.getZz_Cino15(), o.getZz_SPwd());
        return CommonReturnType.create(status);
    }
}
