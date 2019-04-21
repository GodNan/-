package com.css.bdpfnew.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午3:03:45
 * @Description:
 */
@ApiModel(value = "肢体残疾新增所需参数", description = "残疾评定肢体残疾参数")
@Data
@Accessors(fluent = false)
public class DtoBody {

	@ApiModelProperty(value = "uuid-修改功能必填")
	private String uuid;
	@ApiModelProperty(value = "残疾人主键", required = true)
	@NotNull(message = "请选择残疾人")
	private String cdpfId;

	@ApiModelProperty(value = "请求主键")
	private String requestId;
	@ApiModelProperty(value = "残疾等级", required = true)
	@NotNull(message = "请选择残疾等级")
	private Integer bodyLevel;
	@ApiModelProperty(value = "致残主要原因", required = true)
	@NotNull(message = "请选择致残主要原因")
	private Integer bodyReason;
	@ApiModelProperty(value = "残疾情况", required = true)
	@NotNull(message = "请选择残疾情况")
	private Integer bodyDetail;

}
