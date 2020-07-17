package com.zjut.edu.grademanager.Dao;

import com.zjut.edu.grademanager.Model.zhangz_Professions15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionRepository extends JpaRepository<zhangz_Professions15,Integer> {

    List<zhangz_Professions15> findAll();
}
