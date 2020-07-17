package com.zjut.edu.grademanager.DTO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class zhangz_Studentoutview15 {
    /**
     * @author John
     * @date 2020/7/12 10:06
     */

    @Id
    private String zz_Sno15;
    private String zz_Name15;
    private Integer zz_Age15;
    private Integer zz_Sex15;
    private Integer zz_Pno15;
    private String zz_PName15;
    private Integer zz_Cno15;
    private Integer zz_CYear15;
    private String zz_CName15;
    private Integer zz_Prno15;
    private String zz_Prname15;
    private Integer zz_Cino15;
    private String zz_Ciname15;
    private Integer zz_Totalcredit15;
    private Integer zz_Completecredit15;
    private Double zz_Point15;
    private Double zz_GPA15;
    private Integer zz_Orderofschool15;
    private Integer zz_Orderofprofession15;
    private Integer zz_Orderofclass15;
}
