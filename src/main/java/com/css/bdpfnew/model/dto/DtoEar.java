package com.css.bdpfnew.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午2:52:44
 * @Description:
 */
@ApiModel(value = "听力残疾新增所需参数", description = "残疾评定听力残疾参数")
@Data
@Accessors(fluent = false)
public class DtoEar {

	@ApiModelProperty(value = "uuid-修改功能必填")
	private String uuid;
	@ApiModelProperty(value = "残疾人主键", required = true)
	@NotNull(message = "请选择残疾人")
	private String cdpfId;

	@ApiModelProperty(value = "请求主键")
	private String requestId;

	@ApiModelProperty(value = "残疾等级", required = true)
	@NotNull(message = "请选择残疾等级")
	private Integer earLevel;// 残疾等级
	@ApiModelProperty(value = "致残主要原因", required = true)
	@NotNull(message = "请选择致残主要原因")
	private Integer earReason;// 致残主要原因

	private Integer earLeft1;

	private Integer earLeft2;

	private Integer earLeft3;

	private Integer earLeft4;

	private Integer earRight1;

	private Integer earRight2;

	private Integer earRight3;

	private Integer earRight4;

	private Integer noise;

	private Integer hearingLose;

	private Integer earSpeech;

}
