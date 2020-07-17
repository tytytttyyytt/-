package com.zjut.edu.grademanager.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "deleteCourse", procedureName = "deleteCourse", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cono", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "return_value", type = Integer.class),
        })
})
public class zhangz_Courses15 {
    /**
     * @author John
     * @date 2020/7/15 22:23
     */
    @Id
    private Integer zz_Cono15;
    private String zz_Name15;
    private Integer zz_Period15;
    private Integer zz_Way15;
    private Integer zz_Credit15;
}
