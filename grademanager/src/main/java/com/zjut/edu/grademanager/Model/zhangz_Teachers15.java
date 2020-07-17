package com.zjut.edu.grademanager.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class zhangz_Teachers15 {
    /**
     * @author John
     * @date 2020/7/12 10:20
     */
    @Id
    private String zz_Tno15;
    private String zz_Name15;
    private Integer zz_Sex15;
    private Integer zz_Age15;
    private Integer zz_Level15;
    private String zz_Phone15;
    private String zz_TPwd;

}
