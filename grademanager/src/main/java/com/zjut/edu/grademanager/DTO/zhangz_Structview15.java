package com.zjut.edu.grademanager.DTO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class zhangz_Structview15 {
/**
 *@author John
 *@date 2020/7/14 10:21
 */
    private Integer zz_Pno15;
    private String zz_Pname15;
    private Integer zz_Pcount15;
    @Id
    private Integer zz_Cno15;
    private String zz_Cname15;
    private Integer zz_Year15;
    private Integer zz_Ccount15;
}
