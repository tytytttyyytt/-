package com.zjut.edu.grademanager.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class zhangz_Provinces15 {
    /**
     * @author John
     * @date 2020/7/15 16:47
     */
    @Id
    private Integer zz_Prno15;
    private String zz_Name15;
    private Integer zz_Summarycount15;
}
