package com.zjut.edu.grademanager.Dao;

import com.zjut.edu.grademanager.Model.zhangz_Provinces15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvincesRepository extends JpaRepository<zhangz_Provinces15, Integer> {
    // 查找所有省份
    List<zhangz_Provinces15> findAll();
}
