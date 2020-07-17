package com.zjut.edu.grademanager.controller;

import com.zjut.edu.grademanager.Error.BusinessException;
import com.zjut.edu.grademanager.Error.EnumBusinessError;
import com.zjut.edu.grademanager.Model.zhangz_Cities15;
import com.zjut.edu.grademanager.Services.CitiesService;
import com.zjut.edu.grademanager.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/cities")
@RequestMapping("/cities")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class cityController extends baseController {
    /**
     * @author John
     * @date 2020/7/11 20:37
     */

    @Autowired(required = false)
    CitiesService citiesService;
    @Autowired(required = false)
    List<zhangz_Cities15> zhangzCities15List;
    @Autowired(required = false)
    List<zhangz_Cities15> zhangz_cities15ListPrno;

    @RequestMapping("/getAllCitiesInfo")
    @ResponseBody
    public CommonReturnType getAllXCitiesInfo() throws Exception {
        zhangzCities15List = citiesService.findAll();
        if (zhangzCities15List == null) {
            BusinessException businessException = new BusinessException(EnumBusinessError.CITY_LIST_LOAD_FAILED);
            System.out.println(businessException.getErrorMsg());
            throw businessException;
        }
        return CommonReturnType.create(zhangzCities15List);
    }

    @RequestMapping("/getCitiesNum")
    @ResponseBody
    public CommonReturnType getCitiesNum(@RequestParam("city_name") String cityname) throws Exception {
        zhangz_Cities15 num = citiesService.getNumByCity(cityname);
        return CommonReturnType.create(num);
    }

    @RequestMapping("/getCitiesByPrno")
    @ResponseBody
    public CommonReturnType getCitiesNum(@RequestParam("prno") Integer prno) throws Exception {
        zhangz_cities15ListPrno = citiesService.getCityByPrno(prno);
        return CommonReturnType.create(zhangz_cities15ListPrno);
    }

}
