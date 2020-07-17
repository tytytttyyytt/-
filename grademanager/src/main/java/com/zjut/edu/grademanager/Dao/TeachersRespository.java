package com.zjut.edu.grademanager.Dao;

import com.zjut.edu.grademanager.Model.zhangz_Teachers15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeachersRespository extends JpaRepository<zhangz_Teachers15, String> {
    /**
     * @author John
     * @date 2020/7/12 10:25
     */

    // 查找教师
    @Query("select t from zhangz_Teachers15 t where t.zz_Tno15 = ?1")
    zhangz_Teachers15 findByZz_Tno15(String tno);

    // 修改密码
    @Modifying
    @Transactional
    @Query("update zhangz_Teachers15 t set t.zz_TPwd = ?1 where t.zz_Tno15 = ?2")
    int updatePwdByZz_Tno15(String password, String tno);

    // 查找所有教师
    List<zhangz_Teachers15> findAll();

    // 修改教师信息
    @Modifying
    @Transactional
    @Query("update zhangz_Teachers15 t set t.zz_Name15 = ?1 , t.zz_Sex15 = ?2 , t.zz_Age15 = ?3 , t.zz_Level15 = ?4 , t.zz_Phone15 = ?5 , t.zz_TPwd = ?6 where t.zz_Tno15 = ?7")
    int updateTeacherByZz_Tno15(String name, Integer sex, Integer age, Integer level, String phone, String password, String tno);

    // 新增教师
    @Modifying
    @Transactional
    @Query(value = "insert into zhangz_Teachers15 values (?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    int addStudent(String tno, String name, Integer sex, Integer age, Integer level, String phone, String password);

    // 抹去教师信息
    @Modifying
    @Transactional
    @Query("update zhangz_Teachers15 t set t.zz_Name15 = '未启用' , t.zz_Sex15 = -1 , t.zz_Age15 = -1 , t.zz_Level15 = -1 , t.zz_Phone15 = '未启用' , t.zz_TPwd = '未启用' where t.zz_Tno15 = ?1")
    int updateTeacherByZz_Tno15(String tno);
}
