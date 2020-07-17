package com.zjut.edu.grademanager.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
public class ReportService {
    /**
     * @author John
     * @date 2020/7/15 1:11
     */
    @Autowired
    private EntityManager entityManager;

    // 插入或修改成绩记录(由于@Produce注解在sqlserver中失效，因此采用这直接在service中进行调用的方法）
    public Object insertGrade(Integer ono, String sno, Integer grade) {
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("insertOrUpdateGrade");
        storedProcedureQuery.setParameter("ono", ono);
        storedProcedureQuery.setParameter("sno", sno);
        storedProcedureQuery.setParameter("grade", grade);
        storedProcedureQuery.execute();
        return storedProcedureQuery.getOutputParameterValue("return_value");
    }
}
