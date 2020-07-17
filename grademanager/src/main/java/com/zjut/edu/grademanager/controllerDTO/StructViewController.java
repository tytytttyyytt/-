package com.zjut.edu.grademanager.controllerDTO;

import com.zjut.edu.grademanager.ServiceDTO.StructViewService;
import com.zjut.edu.grademanager.controller.baseController;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/StructView")
@RequestMapping("/StructView")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class StructViewController extends baseController {
    /**
     * @author John
     * @date 2020/7/14 10:33
     */
    @Autowired(required = false)
    StructViewService structViewService;
    @Autowired(required = false)
    List zhangz_StructViewProList15;

    @RequestMapping("/getProfession")
    @ResponseBody
    public CommonReturnType getProfession() {
        zhangz_StructViewProList15 = structViewService.getProList();
        return CommonReturnType.create(zhangz_StructViewProList15);
    }
}
