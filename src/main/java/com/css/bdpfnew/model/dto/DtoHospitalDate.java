package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午3:03:45
 * @Description:
 */
@ApiModel(value = "医院设置不同残疾等级预约时间", description = "医院设置不同残疾等级预约时间")
@Data
@Accessors(fluent = false)
public class DtoHospitalDate {
	@ApiModelProperty(value = "医院uuid")
	private String uuid;
	@ApiModelProperty(value = "残疾类型（1-6）")
	private Integer id;
	@ApiModelProperty(value = "预约类别，1按周设置、2按月设置")
	private Integer dataType;
	@ApiModelProperty(value = "周时间")
	private String[] weekDatas;
	@ApiModelProperty(value = "月时间")
	private String[] datas;
	@ApiModelProperty(value = "预约人数")
	private Integer number;
	@ApiModelProperty(value = "收费标准")
	private Integer idtFee;
	@ApiModelProperty(value = "是否需要挂号 (1：是 ,0：否)")
	private Integer regNeeded;
	@ApiModelProperty(value = "评定时间描述")
	private String idtTimeDesc;
	@ApiModelProperty(value = "需要申请人提供的资料及资质要求")
	private String idtDesc;

}
