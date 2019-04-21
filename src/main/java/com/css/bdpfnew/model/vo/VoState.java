package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
* @Author erDuo 
* @Date 2018年7月5日 下午3:43:15
* @Description:
*/
@Accessors
@Data
public class VoState {

	private Integer stateCode;
	private Integer stateType;
	private Integer processId;// 流程id
	private String name;// 流程名
	private String detail;// 流程描述
	private Integer orderNum;
	private String icon;// 流程icon
	private String permissionId;// 权限
}
