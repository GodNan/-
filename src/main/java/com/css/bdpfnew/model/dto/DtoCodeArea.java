package com.css.bdpfnew.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther: Heych
 * @Date: 2018/7/25
 * @Version: 1.0
 * @Description:
 */

@ApiModel(value = "区划操作所需参数", description = "区划操作")
@Data
@Accessors(fluent = false)
public class DtoCodeArea {
	
	@ApiModelProperty(value = "uuid-修改功能必填")
	private String uuid;
	@ApiModelProperty(value = "区划编码", required = true)
	@NotNull(message = "请填写区划编码")
    private String areaCode;
	@ApiModelProperty(value = "区划名称", required = true)
	@NotNull(message = "请填写区划名称")
    private String areaName;
	@ApiModelProperty(value = "区划等级", required = true)
	@NotNull(message = "请填写区划等级")
    private Integer areaLevel;
	@ApiModelProperty(value = "父级区划编码", required = true)
	@NotNull(message = "请填写父级区划编码")
    private String parentAreaCode;
    private String areaAttribute;
    private String memo;
    private String validSign;
    
}
