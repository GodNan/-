package com.css.bdpfnew.model.vo;


import java.util.List;


import lombok.Data;
import lombok.experimental.Accessors;



@Accessors
@Data
public class VoDCodeType {
    private String typeCode;
    private String  description;
    private String  remark;
    private Integer  flag;
    private String   contents;
    
}
