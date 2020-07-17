package com.zjut.edu.grademanager.DaoDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Studentoutview15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentOutViewRepository extends JpaRepository<zhangz_Studentoutview15, String> {

    // 查找所有学生记录
    List<zhangz_Studentoutview15> findAll();

    // 按照学号查找学生记录
    @Query("select s from zhangz_Studentoutview15 s where s.zz_Sno15 = ?1")
    List<zhangz_Studentoutview15> findAllByZz_Sno15(String sno);
}
