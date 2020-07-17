package com.zjut.edu.grademanager.DaoDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Studentsview15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentViewRepository extends JpaRepository<zhangz_Studentsview15, String> {
    // 寻找所有学生信息
    List<zhangz_Studentsview15> findAll();

}
