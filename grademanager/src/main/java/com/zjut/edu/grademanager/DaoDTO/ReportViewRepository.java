package com.zjut.edu.grademanager.DaoDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Reportview15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportViewRepository extends JpaRepository<zhangz_Reportview15, String> {

    // 查找所有成绩记录
    @Query("select r from zhangz_Reportview15 r")
    List<zhangz_Reportview15> findAll();


}
