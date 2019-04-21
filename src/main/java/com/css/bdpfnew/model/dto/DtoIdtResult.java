package com.css.bdpfnew.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @Author erDuo 
* @Date 2018年7月30日 上午10:00:55
* @Description:
*/
@ApiModel(value = "评定结果新增所需参数", description = "评定结果参数")
@Data
@Accessors(fluent = false)
public class DtoIdtResult {

	@ApiModelProperty(value = "uuid-修改功能必填")
	private String uuid;
	@ApiModelProperty(value = "任务主键", required = true)
	@NotNull(message = "请选择残疾人")
	private String requestId;// 请求id
	@ApiModelProperty(value = "残疾等级", required = true)
	@NotNull(message = "请选择残疾等级")
	private Integer idtLevel;// 评残等级
	@ApiModelProperty(value = "残疾类别", required = true)
	@NotNull(message = "请选择残疾类别")
	private Integer idtKind;// 评残类别
	@ApiModelProperty(value = "残疾类别字符串", required = true)
	@NotNull(message = "请选择残疾类别")
	private String kindstr;// 具体类别
	@ApiModelProperty(value = "残疾类别等级字符串", required = true)
	@NotNull(message = "请选择残疾类别")
	private String levelstr;// 具体类别等级
	@ApiModelProperty(value = "证件申请类型", required = false)
	private Integer newType;// ???证件申请类型
	@ApiModelProperty(value = "评定方式", required = true)
	@NotNull(message = "请选择残疾类别")
	private Integer idtType;// ???评定方式
	@ApiModelProperty(value = "评残医院", required = false)
	private String idtHospital;// 评残医院
	@ApiModelProperty(value = "评残医生", required = false)
	private String idtName;// 评残医生
	@ApiModelProperty(value = "处理意见", required = false)
	private String idtComment;// 处理意见
	@ApiModelProperty(value = "评残日期", required = false)
	private Date idtDate;// 评残日期
	@ApiModelProperty(value = "残疾情况描述", required = false)
	private String cjqkDescribe;// 残疾情况描述
	@ApiModelProperty(value = "备注", required = false)
	private String memo;// ???备注（评定结果）

}
