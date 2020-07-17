package com.zjut.edu.grademanager.ServiceDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Opencoursesview15;
import com.zjut.edu.grademanager.DTO.zhangz_Reportview15;
import com.zjut.edu.grademanager.DaoDTO.OpenCoursesViewRepository;
import com.zjut.edu.grademanager.DaoDTO.ReportViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
public class OpenCoursesViewService {
    /**
     * @author John
     * @date 2020/7/13 22:54
     */
    @Autowired(required = false)
    OpenCoursesViewRepository openCoursesViewRepository;
    @Autowired
    private EntityManager entityManager;

    public List<zhangz_Opencoursesview15> getAll() {
        List<zhangz_Opencoursesview15> list = openCoursesViewRepository.findAll();
        System.out.println(list);
        return list;
    }

    // 按照条件查找所有开课记录(由于@Produce注解在sqlserver中失效，因此采用这直接在service中进行调用的方法）
    public List getInfoBySno(String cono, String conname, Integer year, Integer term, Integer ways, String tname, Integer pno, Integer cyear, Integer cno) {
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("getOpenCoursesStu");
        storedProcedureQuery.setParameter("cono", cono);
        storedProcedureQuery.setParameter("conname", conname);
        storedProcedureQuery.setParameter("year", year);
        storedProcedureQuery.setParameter("term", term);
        storedProcedureQuery.setParameter("ways", ways);
        storedProcedureQuery.setParameter("tname", tname);
        storedProcedureQuery.setParameter("pno", pno);
        storedProcedureQuery.setParameter("cyear", cyear);
        storedProcedureQuery.setParameter("cno", cno);
        storedProcedureQuery.execute();
        return storedProcedureQuery.getResultList();
    }

    public List<zhangz_Opencoursesview15> getByTno(String tno) {
        List<zhangz_Opencoursesview15> list = openCoursesViewRepository.findAllByZz_Tno15(tno);
        System.out.println(list);
        return list;
    }

    public List<zhangz_Opencoursesview15> getByYear(String tno, Integer year) {
        List<zhangz_Opencoursesview15> list = openCoursesViewRepository.findAllByZz_Year15(tno, year);
        System.out.println(list);
        return list;
    }

    public List<zhangz_Opencoursesview15> getByTerm(String tno, Integer term) {
        List<zhangz_Opencoursesview15> list = openCoursesViewRepository.findAllByZz_Term15(tno, term);
        System.out.println(list);
        return list;
    }

    public List<zhangz_Opencoursesview15> getByYearAndTerm(String tno, Integer year, Integer term) {
        List<zhangz_Opencoursesview15> list = openCoursesViewRepository.findAllByZz_Year15AAndZz_Term15(tno, year, term);
        System.out.println(list);
        return list;
    }

    public List getCnoByCono(Integer cono){
        List list = openCoursesViewRepository.findByzz_Cono15(cono);
        System.out.println(list);
        return list;
    }
}
