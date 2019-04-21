package com.css.bdpfnew.model.dto.report;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @Author lvmn
 * @Date 2018/11/14 17:22
 * @Description
 */
@Data
public class DtoAge {
    private String cityId;
    private Integer ageGroup;
    private String[] certDate;
}
