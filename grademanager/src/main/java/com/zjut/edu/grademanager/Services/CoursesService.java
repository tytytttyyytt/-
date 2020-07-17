package com.zjut.edu.grademanager.Services;

import com.zjut.edu.grademanager.Dao.CourseRepository;
import com.zjut.edu.grademanager.Model.zhangz_Courses15;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
public class CoursesService {
    /**
     * @author John
     * @date 2020/7/15 22:26
     */

    @Autowired(required = false)
    CourseRepository courseRepository;
    @Autowired
    private EntityManager entityManager;

    public List<zhangz_Courses15> getAllCourses() {
        List<zhangz_Courses15> list = courseRepository.findAll();
        System.out.println(list);
        return list;
    }

    public int updateCourse(String name, Integer period, Integer credit, Integer ways, Integer cono) {
        int status = courseRepository.updateByCono(name, period, credit, ways, cono);
        System.out.println(status);
        return status;
    }

    public int insertCourse(String name, Integer period, Integer ways, Integer credit) {
        int status = courseRepository.insertCourse(name, period, ways, credit);
        System.out.println(status);
        return status;
    }

    // 删除课程记录(由于@Produce注解在sqlserver中失效，因此采用这直接在service中进行调用的方法）
    public Object deleteCourse(Integer cono) {
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("deleteCourse");
        storedProcedureQuery.setParameter("cono", cono);
        storedProcedureQuery.execute();
        return storedProcedureQuery.getOutputParameterValue("return_value");
    }
}
