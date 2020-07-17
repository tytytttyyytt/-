package com.zjut.edu.grademanager.Services;

import com.zjut.edu.grademanager.Dao.OpenCoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

@Service
public class OpenCoursesService {
    /**
     * @author John
     * @date 2020/7/16 15:53
     */

    @Autowired(required = false)
    OpenCoursesRepository openCoursesRepository;
    @Autowired
    private EntityManager entityManager;

    public int insertOpenCourse(Integer cono, Integer cno, Integer year, Integer term, String tno) {
        int status = openCoursesRepository.inertInfoOpencourse(cono, cno, year, term, tno);
        System.out.println(status);
        return status;
    }

    // 删除课课记录(由于@Produce注解在sqlserver中失效，因此采用这直接在service中进行调用的方法）
    public Object deleteOpenCourse(Integer ono) {
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("deleteOpenCourse");
        storedProcedureQuery.setParameter("ono", ono);
        storedProcedureQuery.execute();
        return storedProcedureQuery.getOutputParameterValue("return_value");
    }
}
