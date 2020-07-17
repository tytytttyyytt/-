package com.zjut.edu.grademanager.Dao;

import com.zjut.edu.grademanager.Model.zhangz_Students15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentsRespository extends JpaRepository<zhangz_Students15, String> {
    /**
     * @author John
     * @date 2020/7/12 10:25
     */

    // 查找学生
    @Query("select s from zhangz_Students15 s where s.zz_Sno15 = ?1")
    zhangz_Students15 findByZz_Sno15(String sno);

    // 根据班级号查找学生
    @Query("select s from zhangz_Students15 s where s.zz_Cno15 = ?1")
    List<zhangz_Students15> findByZz_Cno15(Integer cno);

    // 修改密码
    @Modifying
    @Transactional
    @Query("update zhangz_Students15 s set s.zz_SPwd = ?1 where s.zz_Sno15 = ?2")
    int updatePwdByZz_Sno15(String password, String sno);

    // 修改学生信息
    @Modifying
    @Transactional
    @Query("update zhangz_Students15 s set s.zz_Name15 = ?1,s.zz_Sex15 = ?2,s.zz_Age15 = ?3,s.zz_Cno15 = ?4,s.zz_Cino15 = ?5,s.zz_SPwd = ?6 where s.zz_Sno15 = ?7")
    int updateStudentByZz_Sno15(String name, Integer sex, Integer age, Integer cno, Integer cino, String password, String sno);

    // 添加学生信息
    @Modifying
    @Transactional
    @Query(value = "insert into zhangz_Students15 values (?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
    int addStudent(String sno, String name, Integer sex, Integer age, Integer allcredit, Integer cno, Integer cino, String password);
}
