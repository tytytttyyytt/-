package com.zjut.edu.grademanager.Services;

import com.zjut.edu.grademanager.Dao.TeachersRespository;
import com.zjut.edu.grademanager.Model.zhangz_Teachers15;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachersService {
    /**
     * @author John
     * @date 2020/7/11 20:22
     */
    @Autowired(required = false)
    TeachersRespository teachersRespository;

    public zhangz_Teachers15 getTeacher(String tno) {
        zhangz_Teachers15 tea = teachersRespository.findByZz_Tno15(tno);
        System.out.println(tea);
        return tea;
    }

    public int updateTeacherPassword(String password, String tno) {
        int status = teachersRespository.updatePwdByZz_Tno15(password, tno);
        System.out.println(status);
        return status;
    }

    public List<zhangz_Teachers15> getAllTeacher() {
        List<zhangz_Teachers15> list = teachersRespository.findAll();
        System.out.println(list);
        return list;
    }

    public int updateTeacherByTno(String name, Integer sex, Integer age, Integer level, String phone, String password, String tno) {
        int status = teachersRespository.updateTeacherByZz_Tno15(name, sex, age, level, phone, password, tno);
        System.out.println(status);
        return status;
    }

    public int addTeacher(String tno, String name, Integer sex, Integer age, Integer level, String phone, String password) {
        int status = teachersRespository.addStudent(tno, name, sex, age, level, phone, password);
        System.out.println(status);
        return status;
    }

    public int eraseTeacherInfo(String tno){
        int status = teachersRespository.updateTeacherByZz_Tno15(tno);
        System.out.println(status);
        return status;
    }

}
