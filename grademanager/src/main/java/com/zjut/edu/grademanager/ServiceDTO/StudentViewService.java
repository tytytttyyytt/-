package com.zjut.edu.grademanager.ServiceDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Studentsview15;
import com.zjut.edu.grademanager.DaoDTO.StudentViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentViewService {
    /**
     * @author John
     * @date 2020/7/15 16:02
     */
    @Autowired(required = false)
    StudentViewRepository studentViewRepository;

    public List<zhangz_Studentsview15> getAllStudent() {
        List<zhangz_Studentsview15> list = studentViewRepository.findAll();
        System.out.println(list);
        return list;
    }
}
