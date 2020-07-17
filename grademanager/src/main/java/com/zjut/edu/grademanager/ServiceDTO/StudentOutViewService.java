package com.zjut.edu.grademanager.ServiceDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Studentoutview15;
import com.zjut.edu.grademanager.DaoDTO.StudentOutViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentOutViewService {
    /**
     * @author John
     * @date 2020/7/11 22:50
     */
    @Autowired(required = false)
    StudentOutViewRepository studentOutViewRepository;

    public List<zhangz_Studentoutview15> getAll() {
        List<zhangz_Studentoutview15> list = studentOutViewRepository.findAll();
        System.out.println(list);
        return list;
    }

    public List<zhangz_Studentoutview15> getInfoBySno(String sno) {
        List<zhangz_Studentoutview15> list = studentOutViewRepository.findAllByZz_Sno15(sno);
        System.out.println(list);
        return list;
    }
}
