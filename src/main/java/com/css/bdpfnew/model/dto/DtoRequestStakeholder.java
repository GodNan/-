package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @Author erDuo 
* @Date 2018年7月9日 上午9:30:35
* @Description:
*/
@ApiModel(value = "任务处理人所需参数", description = "任务处理人新增参数")
@Data
@Accessors(fluent = false)
public class DtoRequestStakeholder {

	private String requestId;// 请求id
	private String userId;// 不一定用得上
	private String cityid;// 待处理人的区划
	private String permissionId;// 权限
	private Integer delFlag;

}
