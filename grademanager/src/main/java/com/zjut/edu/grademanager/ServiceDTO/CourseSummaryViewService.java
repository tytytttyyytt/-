package com.zjut.edu.grademanager.ServiceDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Coursesummaryview15;
import com.zjut.edu.grademanager.DaoDTO.CourseSummaryViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSummaryViewService {
    /**
     * @author John
     * @date 2020/7/15 11:33
     */
    @Autowired(required = false)
    CourseSummaryViewRepository courseSummaryViewRepository;

    public List<zhangz_Coursesummaryview15> getAllCourseSummary(String tno) {
        List<zhangz_Coursesummaryview15> list = courseSummaryViewRepository.findAllByZz_Tno15(tno);
        System.out.println(list);
        return list;
    }

    public List<zhangz_Coursesummaryview15> getAllCourseSummaryByYear(String tno, Integer year) {
        List<zhangz_Coursesummaryview15> list = courseSummaryViewRepository.findByZz_Year15(tno, year);
        System.out.println(list);
        return list;
    }

    public List<zhangz_Coursesummaryview15> getAllCourseSummaryByTerm(String tno, Integer term) {
        List<zhangz_Coursesummaryview15> list = courseSummaryViewRepository.findByZz_Term15(tno, term);
        System.out.println(list);
        return list;
    }

    public List<zhangz_Coursesummaryview15> getAllCourseSummaryByYearAndTerm(String tno, Integer year, Integer term) {
        List<zhangz_Coursesummaryview15> list = courseSummaryViewRepository.findByZz_Year15AndZz_Term15(tno, year, term);
        System.out.println(list);
        return list;
    }
}
