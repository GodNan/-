package com.css.bdpfnew.model.dto;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author HEYCH
 * @Date 2018/11/26 10:22
 * @Description
 */
@ApiModel
@Data
@Accessors(fluent  = false)
public class DtoDiaoquParams {
    @ApiModelProperty(notes = "身份证号码", required = true)
    @NotBlank(message = "请输入身份证号码")
    private String citizenId;

    @ApiModelProperty(notes = "姓名", required = true)
    @NotBlank(message = "请输入姓名")
    private String citizenName;

}
