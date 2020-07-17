package com.zjut.edu.grademanager.ServiceDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Reportview15;
import com.zjut.edu.grademanager.DaoDTO.ReportViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
public class ReportViewService {
    /**
     * @author John
     * @date 2020/7/11 22:50
     */
    @Autowired(required = false)
    ReportViewRepository reportViewRepository;
    @Autowired
    private EntityManager entityManager;

    public List<zhangz_Reportview15> getAll() {
        List<zhangz_Reportview15> list = reportViewRepository.findAll();
        System.out.println(list);
        return list;
    }

    // 按照学号,学年，学期查找所有成绩记录(由于@Produce注解在sqlserver中失效，因此采用这直接在service中进行调用的方法）
    public List getInfoBySno(String sno, Integer year, Integer term) {
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("getGradeStu");
        storedProcedureQuery.setParameter("sno", sno);
        storedProcedureQuery.setParameter("year", year);
        storedProcedureQuery.setParameter("term", term);
        storedProcedureQuery.execute();
        return storedProcedureQuery.getResultList();
    }

    // 按照班级编号以及课程编号查找所有成绩记录(由于@Produce注解在sqlserver中失效，因此采用这直接在service中进行调用的方法）
    public List getInfoByCno(Integer cno ,Integer cono) {
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("getReportView");
        storedProcedureQuery.setParameter("cno", cno);
        storedProcedureQuery.setParameter("cono", cono);
        storedProcedureQuery.execute();
        return storedProcedureQuery.getResultList();
    }
}
