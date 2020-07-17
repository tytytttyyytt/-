package com.zjut.edu.grademanager.Dao;

import com.zjut.edu.grademanager.Model.ReportKeys;
import com.zjut.edu.grademanager.Model.zhangz_Reports15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<zhangz_Reports15, ReportKeys> {
}
