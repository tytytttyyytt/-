package com.zjut.edu.grademanager.Services;

import com.zjut.edu.grademanager.Dao.TermAndYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TermAndYearService {
    /**
     * @author John
     * @date 2020/7/11 20:22
     */
    @Autowired(required = false)
    TermAndYearRepository termAndYearRepository;

    @Transactional
    public List findAllYears(){
        List list = termAndYearRepository.findByZz_Year15();
        System.out.println(list);
        return list;
    }

    public List findAllTerms(){
        List list = termAndYearRepository.findByZz_Term15();
        System.out.println(list);
        return list;
    }

}
