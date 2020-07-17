package com.zjut.edu.grademanager.Services;

import com.zjut.edu.grademanager.Dao.XClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XClassesService {
    /**
     * @author John
     * @date 2020/7/14 10:53
     */

    @Autowired(required = false)
    XClassesRepository xClassesRepository;

    public List getAllYears(Integer pno) {
        List list = xClassesRepository.findByZz_Pno15(pno);
        System.out.println(list);
        return list;
    }

    public List getAllClasses(Integer pno, Integer year) {
        List list = xClassesRepository.findClass(pno, year);
        System.out.println(list);
        return list;
    }
}
