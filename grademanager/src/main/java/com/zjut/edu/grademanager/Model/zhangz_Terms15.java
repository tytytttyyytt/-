package com.zjut.edu.grademanager.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@IdClass(TermKeys.class)
@Entity
public class zhangz_Terms15 {
/**
 *@author John
 *@date 2020/7/13 17:52
 */
    @Id
    private Integer zz_Year15;
    @Id
    private Integer zz_Term15;
}
