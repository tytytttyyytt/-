package com.zjut.edu.grademanager.ServiceDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Reportsummaryview15;
import com.zjut.edu.grademanager.DaoDTO.ReportSummaryViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
public class ReportSummaryViewService {
    /**
     * @author John
     * @date 2020/7/11 22:50
     */
    @Autowired(required = false)
    ReportSummaryViewRepository reportSummaryViewRepository;
    @Autowired
    private EntityManager entityManager;

    public List<zhangz_Reportsummaryview15> getAll() {
        List<zhangz_Reportsummaryview15> list = reportSummaryViewRepository.findAll();
        System.out.println(list);
        return list;
    }


    // 按照学号查找所有成绩汇总记录(由于@Produce注解在sqlserver中失效，因此采用这直接在service中进行调用的方法）
    public List getInfoBySno(String sno) {
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("getReportSummaryView");
        storedProcedureQuery.setParameter("sno", sno);
        storedProcedureQuery.execute();
        return storedProcedureQuery.getResultList();
    }
}
