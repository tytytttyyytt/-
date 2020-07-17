package com.zjut.edu.grademanager.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "deleteStudent", procedureName = "deleteStudent", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sno", type = String.class),
        })
})
public class zhangz_Students15 {
    /**
     * @author John
     * @date 2020/7/12 10:20
     */
    @Id
    private String zz_Sno15;
    private String zz_Name15;
    private Integer zz_Sex15;
    private Integer zz_Age15;
    private Integer zz_Totalcredit15;
    private Integer zz_Cno15;
    private Integer zz_Cino15;
    private String zz_SPwd;

}
