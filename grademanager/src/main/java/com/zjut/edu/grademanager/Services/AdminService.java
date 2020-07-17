package com.zjut.edu.grademanager.Services;

import com.zjut.edu.grademanager.Dao.AdminRespository;
import com.zjut.edu.grademanager.Model.zhangz_Admin15;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    /**
     * @author John
     * @date 2020/7/11 20:22
     */
    @Autowired(required = false)
    AdminRespository adminRespository;

    public zhangz_Admin15 getAdmin(String ano) {
        zhangz_Admin15 admin = adminRespository.findByZz_Ano15(ano);
        System.out.println(admin);
        return admin;
    }

    public int updateAdminPassword(String password, String ano) {
        int status = adminRespository.updatePwdByZz_Ano15(password, ano);
        System.out.println(status);
        return status;
    }

}
