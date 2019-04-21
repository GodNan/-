package com.css.bdpfnew.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年7月6日 上午10:26:23
 * @Description:
 */
@ApiModel(value = "任务处理记录", description = "任务处理记录")
@Data
@Accessors(fluent = false)
public class DtoRequestNote {
	@ApiModelProperty(value = "请求任务id", required = true)
	@NotNull(message = "请选择操作任务")
	private String requestId;
	@ApiModelProperty(value = "操作用户id", required = true)
	@NotNull(message = "当前用户未登录或信息丢失")
	private String userId;
	@ApiModelProperty(value = "操作用户姓名", required = true)
	@NotNull(message = "当前用户未登录或信息丢失")
	private String userName;
	@ApiModelProperty(value = "处理意见")
	private String note;
	@ApiModelProperty(value = "任务处理前状态", required = true)
	@NotNull(message = "此残疾人当前任务状态异常")
	private Integer handleState;
	@ApiModelProperty(value = "任务处理后状态", required = true)
	@NotNull(message = "请选择处理结果")
	private Integer finishState;
	@ApiModelProperty(value = "任务操作类型/处理结果", required = true)
	@NotNull(message = "请选择处理结果")
	private Integer handleType;
	@ApiModelProperty(value = "是否入户评残", required = true)
	@NotNull(message = "请选择是否入户评残")
	private Integer idtAtHome;

}
