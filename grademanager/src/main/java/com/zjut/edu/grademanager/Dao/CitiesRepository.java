package com.zjut.edu.grademanager.Dao;

import com.zjut.edu.grademanager.Model.zhangz_Cities15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository extends JpaRepository<zhangz_Cities15,Integer> {
/**
 *@author John
 *@date 2020/7/11 20:14
 */
    // 查找所有城市
    List<zhangz_Cities15> findAll();

    // 查找城市所在的人数
    @Query("select c from zhangz_Cities15 c where c.zz_Name15 = ?1")
    zhangz_Cities15 findByZz_Name15(String cityName);

    // 按照省份查找城市
    @Query("select c from zhangz_Cities15 c where c.zz_Prno15 = ?1")
    List<zhangz_Cities15> findByZz_Prno15(Integer prno);
}
