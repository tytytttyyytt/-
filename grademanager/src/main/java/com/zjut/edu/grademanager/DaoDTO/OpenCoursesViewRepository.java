package com.zjut.edu.grademanager.DaoDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Opencoursesview15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenCoursesViewRepository extends JpaRepository<zhangz_Opencoursesview15, Integer> {

    // 查找所有开课记录
    List<zhangz_Opencoursesview15> findAll();

    // 按照教师编号查找开课记录
    @Query("select o from zhangz_Opencoursesview15 o where o.zz_Tno15 = ?1")
    List<zhangz_Opencoursesview15> findAllByZz_Tno15(String tno);

    // 按照学年查找开课记录
    @Query("select o from zhangz_Opencoursesview15 o where o.zz_Tno15 = ?1 and o.zz_Year15 = ?2")
    List<zhangz_Opencoursesview15> findAllByZz_Year15(String tno, Integer year);

    // 按照学期查找开课记录
    @Query("select o from zhangz_Opencoursesview15 o where o.zz_Tno15 = ?1 and o.zz_Term15 = ?2")
    List<zhangz_Opencoursesview15> findAllByZz_Term15(String tno, Integer term);

    // 按照学年以及学期查找开课记录
    @Query("select o from zhangz_Opencoursesview15 o where o.zz_Tno15 = ?1 and o.zz_Year15 = ?2 and o.zz_Term15 = ?3")
    List<zhangz_Opencoursesview15> findAllByZz_Year15AAndZz_Term15(String tno, Integer year, Integer term);

    // 根据课程编号查询班级
    @Query("select o.zz_Cno15 from zhangz_Opencoursesview15 o where o.zz_Cono15 = ?1")
    List findByzz_Cono15(Integer cono);

}
