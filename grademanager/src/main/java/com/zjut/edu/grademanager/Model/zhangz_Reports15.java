package com.zjut.edu.grademanager.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@IdClass(ReportKeys.class)
@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "insertOrUpdateGrade", procedureName = "insertOrUpdateGrade", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ono", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sno", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "grade", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "return_value", type = Integer.class),
        })
})
public class zhangz_Reports15 {
/**
 *@author John
 *@date 2020/7/13 17:52
 */
    @Id
    private Integer zz_Ono15;
    @Id
    private String zz_Sno15;

    private Integer zz_Grade15;
}
