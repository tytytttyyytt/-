package com.zjut.edu.grademanager.DTO;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getOpenCoursesStu", procedureName = "getOpenCoursesStu", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cono", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "conname", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "year", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "term", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ways", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "tname", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pno", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cyear", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cno", type = Integer.class),
        })
})
public class zhangz_Opencoursesview15 {
    /**
     * @author John
     * @date 2020/7/13 22:47
     */
    @Id

    private Integer zz_Ono15;
    private Integer zz_Cono15;
    private String zz_Coname15;
    private Integer zz_Credit15;
    private Integer zz_Period15;
    private Integer zz_Way15;
    private Integer zz_Year15;
    private Integer zz_Term15;
    private Integer zz_Cno15;
    private String zz_CName15;
    private Integer zz_CYear15;
    private Integer zz_Pno15;
    private String zz_PName15;
    private String zz_Tno15;
    private String zz_Tname15;
    private Integer zz_Tage15;
    private Integer zz_Tsex15;
    private Integer zz_Tlevel15;
    private String zz_Tphone15;
}
