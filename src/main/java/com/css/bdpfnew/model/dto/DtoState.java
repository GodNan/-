package com.css.bdpfnew.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年7月5日 下午4:56:14
 * @Description:
 */
@ApiModel(value = "流程状态", description = "流程状态新增/查询")
@Data
@Accessors(fluent = false)
public class DtoState {
	@ApiModelProperty(value = "流程id", required = true)
	@NotNull(message = "请选择流程")
	private Integer processId;
	@ApiModelProperty(value = "状态码")
	private Integer stateCode;
	@ApiModelProperty(value = "状态码")
	private Integer stateType;
	@ApiModelProperty(value = "状态码")
	private String name;
	@ApiModelProperty(value = "状态描述")
	private String detail;
	@ApiModelProperty(value = "状态排序")
	private Integer orderNum;
	@ApiModelProperty(value = "状态icon")
	private String icon;

}
