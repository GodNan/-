package com.css.bdpfnew.model.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;



@ApiModel(value = "字典项操作参数", description = "测试")
@Data
@Accessors(fluent = false)
public class DtoDCode {

	    @ApiModelProperty(value = "uuid")
	    private String uuid;

	    @ApiModelProperty(value = "字典项编码-必填",required = true)
	    private String code;

	    @ApiModelProperty(value = "字典项名称-必填",required = true)
	    private String description;

	    @ApiModelProperty(value = "字典项备注--非必填")
	    private String remark;
	    
	    @ApiModelProperty(value = "字典项序号-必填",required = true)
	    private Integer orderNum;
	    
	    @ApiModelProperty(value = "字典类型-必填",required = true)
	    private String typeCode;

	    @ApiModelProperty(value = "字典项状态--必填",required = true)
	    private Integer flag;
}
