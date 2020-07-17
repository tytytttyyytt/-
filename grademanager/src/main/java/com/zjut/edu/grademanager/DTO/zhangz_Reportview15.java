package com.zjut.edu.grademanager.DTO;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getGradeStu", procedureName = "getGradeStu", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sno", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "year", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "term", type = Integer.class),
        }),
        @NamedStoredProcedureQuery(name = "getReportView", procedureName = "getReportView", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cno", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cono", type = Integer.class),
        })
})
public class zhangz_Reportview15 {
    /**
     * @author John
     * @date 2020/7/13 0:45
     */
    @Id

    private Integer zz_Grade15;
    private String zz_Sno15;
    private String zz_SName15;
    private Integer zz_SSex15;
    private Integer zz_SAge15;
    private Integer zz_Cno15;
    private Integer zz_CYear15;
    private String zz_CName15;
    private Integer zz_Pno15;
    private String zz_PName15;
    private Integer zz_Cino15;
    private String zz_Ciname15;
    private Integer zz_Prno15;
    private String zz_Prname15;
    private Integer zz_Ono15;
    private Integer zz_Cono15;
    private String zz_Coname15;
    private Integer zz_Credit15;
    private Integer zz_Creditget15;
    private Integer zz_Period15;
    private Integer zz_Way15;
    private Integer zz_Term15;
    private Integer zz_Tno15;
    private String zz_Tname15;
    private Integer zz_Tage15;
    private Integer zz_Tsex15;
    private Integer zz_Tlevel15;
    private String zz_Tphone15;
}
