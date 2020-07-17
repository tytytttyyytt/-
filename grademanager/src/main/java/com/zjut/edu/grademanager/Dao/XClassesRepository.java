package com.zjut.edu.grademanager.Dao;

import com.zjut.edu.grademanager.Model.zhangz_Xclasses15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XClassesRepository extends JpaRepository<zhangz_Xclasses15, Integer> {

    @Query("select distinct x.zz_Year15 from zhangz_Xclasses15 x where x.zz_Pno15 = ?1")
    List findByZz_Pno15(Integer pno);

    @Query("select distinct x.zz_Cno15, x.zz_Name15 from zhangz_Xclasses15 x where x.zz_Pno15 = ?1 and x.zz_Year15 = ?2")
    List findClass(Integer pno, Integer year);
}
