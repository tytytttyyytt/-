package com.zjut.edu.grademanager.Services;

import com.zjut.edu.grademanager.Dao.ProfessionRepository;
import com.zjut.edu.grademanager.Model.zhangz_Professions15;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionService {
/**
 *@author John
 *@date 2020/7/14 10:53
 */

    @Autowired(required = false)
    ProfessionRepository professionRepository;

    public List<zhangz_Professions15> getAllProfession(){
        List<zhangz_Professions15> list = professionRepository.findAll();
        System.out.println(list);
        return list;
    }
}
