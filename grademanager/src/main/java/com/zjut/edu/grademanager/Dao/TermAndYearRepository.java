package com.zjut.edu.grademanager.Dao;

import com.zjut.edu.grademanager.Model.zhangz_Terms15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermAndYearRepository extends JpaRepository<zhangz_Terms15, Integer> {
    /**
     * @author John
     * @date 2020/7/13 17:55
     */
    @Query(value = "select distinct zz_Year15 from zhangz_Terms15", nativeQuery = true)
    List findByZz_Year15();

    @Query(value = "select distinct zz_Term15 from zhangz_Terms15", nativeQuery = true)
    List findByZz_Term15();
}
