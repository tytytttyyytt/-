package com.zjut.edu.grademanager.Services;

import com.zjut.edu.grademanager.Dao.StudentsRespository;
import com.zjut.edu.grademanager.Model.zhangz_Students15;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
public class StudentsService {
    /**
     * @author John
     * @date 2020/7/11 20:22
     */
    @Autowired(required = false)
    StudentsRespository studentsRespository;
    @Autowired(required = false)
    private EntityManager entityManager;

    public zhangz_Students15 getStudent(String sno) {
        zhangz_Students15 stu = studentsRespository.findByZz_Sno15(sno);
        System.out.println(stu);
        return stu;
    }

    public List<zhangz_Students15> getStudentByCno(Integer cno) {
        List<zhangz_Students15> stu = studentsRespository.findByZz_Cno15(cno);
        System.out.println(stu);
        return stu;
    }

    public int updateStudentPassword(String password, String sno) {
        int status = studentsRespository.updatePwdByZz_Sno15(password, sno);
        System.out.println(status);
        return status;
    }

    public int updateStudentByZz_Sno15(String name, Integer sex, Integer age, Integer cno, Integer cino, String password, String sno) {
        int status = studentsRespository.updateStudentByZz_Sno15(name, sex, age, cno, cino, password, sno);
        System.out.println(status);
        return status;
    }

    public List deleteStudentByZz_Sno15(String sno) {
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("deleteStudent");
        storedProcedureQuery.setParameter("sno", sno);
        storedProcedureQuery.execute();
        return storedProcedureQuery.getResultList();
    }

    public int addStudent(String sno, String name, Integer sex, Integer age, Integer allcredit, Integer cno, Integer cino, String password){
        int status = studentsRespository.addStudent(sno, name, sex, age, allcredit, cno, cino, password);
        System.out.println(status);
        return status;
    }
}
