package com.zjut.edu.grademanager.Dao;

import com.zjut.edu.grademanager.Model.zhangz_Opencourses15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OpenCoursesRepository extends JpaRepository<zhangz_Opencourses15, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into zhangz_OpenCourses15 values (?1,?2,?3,?4,?5)")
    int inertInfoOpencourse(Integer cono, Integer cno, Integer year, Integer term, String tno);
}
