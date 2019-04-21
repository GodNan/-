/**
 * 
 */
package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年9月29日 下午2:37:27
 * @Description
 */

@ApiModel(value = "新建任务所需参数", description = "新建任务所需参数")
@Data
@Accessors(fluent = false)
public class DtoNewTask {
	private Integer processId;
	private String cdpfId;
	private Integer logoutReason;
	private String logoutResumeReason;
}