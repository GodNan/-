package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年7月9日 上午9:25:36
 * @Description:
 */
@ApiModel(value = "任务处理所需参数", description = "任务处理残疾参数")
@Data
@Accessors(fluent = false)
public class DtoTask {
	private DtoRequestNote requestNote;
	private DtoRequestStakeholder requestStakeholder;
	private Integer processId;

}
