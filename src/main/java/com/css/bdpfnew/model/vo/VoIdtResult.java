package com.css.bdpfnew.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年7月30日 上午10:10:18
 * @Description:
 */
@Accessors
@Data
public class VoIdtResult {

	private String uuid;
	private String requestId;// 请求id
	private Integer idtLevel;// 评残等级
	private Integer idtKind;// 评残类别
	private String kindstr;// 具体类别
	private String levelstr;// 具体类别等级
	private Integer newType;// ???证件申请类型
	private Integer idtType;// ???评定方式
	private String idtHospital;// 评残医院
	private String idtName;// 评残医生
	private String idtComment;// 处理意见
	private Date idtDate;// 评残日期
	private String cjqkDescribe;// 残疾情况描述
	private String memo;// ???备注（评定结果）

}
