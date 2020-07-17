package com.zjut.edu.grademanager.ServiceDTO;

import com.zjut.edu.grademanager.DaoDTO.StructViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StructViewService {
    /**
     * @author John
     * @date 2020/7/14 10:30
     */
    @Autowired(required = false)
    StructViewRepository structViewRepository;

    public List getProList() {
        List list = structViewRepository.findDistinct();
        System.out.println(list);
        return list;
    }
}
