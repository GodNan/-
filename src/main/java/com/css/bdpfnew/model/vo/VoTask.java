package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
* @Author erDuo 
* @Date 2018年7月23日 上午10:45:37
* @Description:
*/
@Accessors
@Data
public class VoTask {

	private String cdpfId; // 残疾人主键
	private Integer processId;// 流程id
	private String title;
	private String userId;// 初始操作人id
	private String userName;// 初始操作人姓名
	private String citizenName;// 残疾人姓名
	private String citizenId;// 残疾人身份证号
	private String cityid;// 区划id
	private Integer currentState;// 当前状态
	private Integer formerState;// 前一状态

}
