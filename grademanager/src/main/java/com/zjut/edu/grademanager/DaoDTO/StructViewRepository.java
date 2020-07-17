package com.zjut.edu.grademanager.DaoDTO;

import com.zjut.edu.grademanager.DTO.zhangz_Structview15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StructViewRepository extends JpaRepository<zhangz_Structview15,Integer> {

    @Query("select distinct s.zz_Pname15 from zhangz_Structview15 s")
    List findDistinct();
}
