package com.zjut.edu.grademanager.DTO;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getReportSummaryView", procedureName = "getReportSummaryView", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sno", type = String.class)
        })
})
public class zhangz_Reportsummaryview15 {
/**
 *@author John
 *@date 2020/7/11 22:38
 */


    private String zz_Sno15;
    private String zz_Sname15;
    private Integer zz_Ssex15;
    private Integer zz_Sage15;
    private Integer zz_Cno15;
    private Integer zz_Cyear15;
    private String zz_Cname15;
    private Integer zz_Pno15;
    private String zz_Pname15;
    private Integer zz_Year15;
    private Integer zz_Term15;
    private Integer zz_Totalcredit15;
    private Integer zz_Completecredit15;
    private Double zz_Point15;
    private Double zz_gpa15;
    @Id
    private Integer zz_Orderofschool15;
    private Integer zz_Orderofprofession15;
    private Integer zz_Orderofclass15;
}
