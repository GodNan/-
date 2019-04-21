package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors
@Data
public class VoCodeArea {
	
    private String areaCode;
    private String areaName;
    private Integer areaLevel;
    private String parentAreaCode;
    private String areaAttribute;
    private String memo;
    private String validSign;
}
