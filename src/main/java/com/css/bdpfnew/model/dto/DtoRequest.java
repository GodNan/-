package com.css.bdpfnew.model.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月14日 下午2:53:33
 * @Description:
 */
@ApiModel(value = "任务操作查询参数", description = "任务中心相关菜单的查询参数")
@Data
@Accessors(fluent = false)
public class DtoRequest {

	@ApiModelProperty(value = "区划")
	@NotBlank(message = "请选择区划")
	private String cityid;
	
	@ApiModelProperty(value = "任务处理区划")
	private String cityidHolder;
	
	@ApiModelProperty(value = "权限")
	@Size(min=1, message = "请选择权限")
	private String[] permissions;

	@ApiModelProperty(value = "残疾人姓名")
	private String citizenName;

	@ApiModelProperty(value = "残疾人身份证号")
	private String citizenId;

	@ApiModelProperty(value = "任务流程")
	private String processId;

	// 可能用不到, 操作人员只能查询到他有权限操作的环节
	// 普通操作人员只能操作一个环节
	@ApiModelProperty(value = "当前环节")
	private String currentState;

	@ApiModelProperty(value = "起始时间")
	private String startDate;

	@ApiModelProperty(value = "结束时间")
	private String endDate;

}
