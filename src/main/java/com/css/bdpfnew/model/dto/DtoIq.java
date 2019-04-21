package com.css.bdpfnew.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午3:08:15
 * @Description:
 */
@ApiModel(value = "智力残疾新增所需参数", description = "残疾评定智力残疾参数")
@Data
@Accessors(fluent = false)
public class DtoIq {
	@ApiModelProperty(value = "uuid-修改功能必填")
	private String uuid;
	@ApiModelProperty(value = "残疾人主键", required = true)
	@NotNull(message = "请选择残疾人")
	private String cdpfId;

	@ApiModelProperty(value = "请求主键")
	private String requestId;
	@ApiModelProperty(value = "残疾等级", required = true)
	@NotNull(message = "请选择残疾等级")
	private Integer iqLevel;
	@ApiModelProperty(value = "致残主要原因", required = true)
	@NotNull(message = "请选择致残主要原因")
	private Integer iqReason;
	private Integer iqDegree1;
	private Integer iqDegree2;
	private Integer iqValue;

}
