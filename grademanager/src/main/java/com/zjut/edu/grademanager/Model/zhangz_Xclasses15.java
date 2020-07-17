package com.zjut.edu.grademanager.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class zhangz_Xclasses15 {
    /**
     * @author John
     * @date 2020/7/14 10:51
     */
    @Id
    private Integer zz_Cno15;
    private String zz_Name15;
    private Integer zz_Pno15;
    private Integer zz_Year15;
}
