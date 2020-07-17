package com.zjut.edu.grademanager.DaoDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Coursesummaryview15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSummaryViewRepository extends JpaRepository<zhangz_Coursesummaryview15, Integer> {

    // 查询所有成绩记录
    @Query("select c from zhangz_Coursesummaryview15 c where c.zz_Tno15 = ?1")
    List<zhangz_Coursesummaryview15> findAllByZz_Tno15(String tno);

    // 按照学年查询成绩记录
    @Query("select c from zhangz_Coursesummaryview15 c where c.zz_Tno15 = ?1 and c.zz_Year15 = ?2")
    List<zhangz_Coursesummaryview15> findByZz_Year15(String tno, Integer year);

    // 按照学期查询成绩记录
    @Query("select c from zhangz_Coursesummaryview15 c where c.zz_Tno15 = ?1 and c.zz_Term15 = ?2")
    List<zhangz_Coursesummaryview15> findByZz_Term15(String tno, Integer term);

    // 按照学年、学期查询成绩记录
    @Query("select c from zhangz_Coursesummaryview15 c where c.zz_Tno15 = ?1 and c.zz_Year15 = ?2 and c.zz_Term15 = ?3")
    List<zhangz_Coursesummaryview15> findByZz_Year15AndZz_Term15(String tno, Integer year, Integer term);
}
