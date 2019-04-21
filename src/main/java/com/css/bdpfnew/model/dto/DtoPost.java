/**
 * 
 */
package com.css.bdpfnew.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年11月5日 上午10:52:33
 * @Description
 */

@ApiModel(value = "公示记录", description = "公示记录")
@Data
@Accessors(fluent = false)
public class DtoPost {
	// 基本信息
	@ApiModelProperty(value = "uuid-修改功能必填")
	private String uuid;
	@ApiModelProperty(value = "残疾人id", required = true)
	@NotNull(message = "请选择残疾人")
	private String cdpfId;
	@ApiModelProperty(value = "请求任务id", required = true)
	@NotNull(message = "请选择操作任务")
	private String requestId;
	@ApiModelProperty(value = "经办人操作用户id", required = true)
	@NotNull(message = "当前用户未登录或信息丢失")
	private String postUserId;
	@ApiModelProperty(value = "经办人操作用户姓名", required = true)
	@NotNull(message = "当前用户未登录或信息丢失")
	private String postUserName;
	@ApiModelProperty(value = "公示问题")
	private String note;
	@ApiModelProperty(value = "公示结果", required = true)
	@NotNull(message = "请选择公示结果")
	private Integer postType;

}
