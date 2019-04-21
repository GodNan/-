package com.css.bdpfnew.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午2:58:08
 * @Description:
 */
@ApiModel(value = "言语残疾新增所需参数", description = "残疾评定言语残疾参数")
@Data
@Accessors(fluent = false)
public class DtoSpeech {

	@ApiModelProperty(value = "uuid-修改功能必填")
	private String uuid;
	@ApiModelProperty(value = "残疾人主键", required = true)
	@NotNull(message = "请选择残疾人")
	private String cdpfId;

	@ApiModelProperty(value = "请求主键")
	private String requestId;
	@ApiModelProperty(value = "残疾等级", required = true)
	@NotNull(message = "请选择残疾等级")
	private Integer speechLevel;
	@ApiModelProperty(value = "致残主要原因", required = true)
	@NotNull(message = "请选择致残主要原因")
	private Integer speechReason;
	@ApiModelProperty(value = "致残类别", required = true)
	@NotNull(message = "请选择致残类别")
	private Integer speechDis;
	private Integer speechClear; // 语音清晰度
	@ApiModelProperty(value = "言语能力", required = true)
	@NotNull(message = "请选择言语能力")
	private Integer speechFunc;

}
