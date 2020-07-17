package com.zjut.edu.grademanager.DaoDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Reportsummaryview15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportSummaryViewRepository extends JpaRepository<zhangz_Reportsummaryview15, Integer> {

    // 查找所有成绩记录
    List<zhangz_Reportsummaryview15> findAll();

    // 按照学号查找所有成绩记录
    @Query("select r from zhangz_Reportsummaryview15 r where r.zz_Sno15 = ?1")
    List<zhangz_Reportsummaryview15> findByZz_Sno15(String sno);
}
