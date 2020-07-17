package com.zjut.edu.grademanager.Dao;

import com.zjut.edu.grademanager.Model.zhangz_Courses15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<zhangz_Courses15, Integer> {
    // 查找所有课程
    List<zhangz_Courses15> findAll();

    // 更新课程
    @Modifying
    @Transactional
    @Query("update zhangz_Courses15 s set s.zz_Name15 = ?1,s.zz_Period15 = ?2,s.zz_Credit15 = ?3,s.zz_Way15 = ?4 where s.zz_Cono15 = ?5")
    int updateByCono(String name, Integer period, Integer credit, Integer ways, Integer cono);

    // 新建课程
    @Modifying
    @Transactional
    @Query(value = "insert into zhangz_Courses15 values (?1,?2,?3,?4)", nativeQuery = true)
    int insertCourse(String name, Integer period, Integer ways, Integer credit);
}
