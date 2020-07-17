package com.zjut.edu.grademanager.Services;

import com.zjut.edu.grademanager.Dao.ProvincesRepository;
import com.zjut.edu.grademanager.Model.zhangz_Provinces15;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvincesService {
    /**
     * @author John
     * @date 2020/7/15 16:50
     */
    @Autowired(required = false)
    ProvincesRepository provincesRepository;

    public List<zhangz_Provinces15> getAllProvinces() {
        List<zhangz_Provinces15> list = provincesRepository.findAll();
        System.out.println(list);
        return list;
    }

}
