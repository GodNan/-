package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;



@ApiModel(value = "字典表操作参数", description = "测试")
@Data
@Accessors(fluent = false)
public class DtoDCodeType {

	    @ApiModelProperty(value = "uuid")
	    private String uuid;

	    @ApiModelProperty(value = "字典ID-必填",required = true)
	    private String typeCode;

	    @ApiModelProperty(value = "字典名称-必填",required = true)
	    private String description;

	    @ApiModelProperty(value = "字典描述--非必填")
	    private String remark;

	    @ApiModelProperty(value = "字典状态--必填",required = true)
	    private Integer flag;
}
