package com.zjut.edu.grademanager.Services;

import com.zjut.edu.grademanager.Dao.CitiesRepository;
import com.zjut.edu.grademanager.Model.zhangz_Cities15;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CitiesService {
    /**
     * @author John
     * @date 2020/7/11 20:22
     */
    @Autowired(required = false)
    CitiesRepository citiesRepository;

    @Transactional
    public List<zhangz_Cities15> findAll() {
        List<zhangz_Cities15> list = citiesRepository.findAll();
        System.out.println(list);
        return list;
    }

    public zhangz_Cities15 getNumByCity(String cityname) {
        zhangz_Cities15 city = citiesRepository.findByZz_Name15(cityname);
        System.out.println(city);
        return city;
    }

    public List<zhangz_Cities15> getCityByPrno(Integer prno) {
        List<zhangz_Cities15> list = citiesRepository.findByZz_Prno15(prno);
        System.out.println(list);
        return list;
    }
}
