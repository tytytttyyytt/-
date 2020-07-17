package com.zjut.edu.grademanager.Dao;

import com.zjut.edu.grademanager.Model.zhangz_Admin15;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRespository extends JpaRepository<zhangz_Admin15, String> {
    /**
     * @author John
     * @date 2020/7/12 10:25
     */

    // 查找管理员
    @Query("select t from zhangz_Admin15 t where t.zz_Ano15 = ?1")
    zhangz_Admin15 findByZz_Ano15(String ano);

    // 修改密码
    @Modifying
    @Transactional
    @Query("update zhangz_Admin15 t set t.zz_Apwd15 = ?1 where t.zz_Ano15 = ?2")
    int updatePwdByZz_Ano15(String password, String ano);
}
