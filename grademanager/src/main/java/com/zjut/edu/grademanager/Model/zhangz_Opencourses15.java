package com.zjut.edu.grademanager.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "deleteOpenCourse", procedureName = "deleteOpenCourse", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ono", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "return_value", type = Integer.class),
        })
})
public class zhangz_Opencourses15 {
    /**
     * @author John
     * @date 2020/7/16 15:47
     */
    @Id
    private Integer zz_Ono15;
    private Integer zz_Cono15;
    private Integer zz_Cno15;
    private Integer zz_Year15;
    private Integer zz_Term15;
    private String zz_Tno15;
}
