package com.zjut.edu.grademanager.Model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class zhangz_Cities15 {
/**
 *@author John
 *@date 2020/7/11 20:08
 */
    @Id
    private Integer zz_Cino15;

    private String zz_Name15;
    private Integer zz_Prno15;
    private Integer zz_Summarycount15;
}
