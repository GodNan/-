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
 * @Date 2018年10月29日 上午11:05:08
 * @Description
 */
@ApiModel(value = "评定照片新增所需参数", description = "评定照片参数")
@Data
@Accessors(fluent = false)
public class DtoIdtPhoto {
	@ApiModelProperty(value = "身份证号", required = true)
	@NotNull(message = "请选择残疾人")
	private String citizenId;
	@ApiModelProperty(value = "任务id", required = true)
	@NotNull(message = "请选择任务")
	private String idtRecordUuid;
	@ApiModelProperty(value = "评残类别", required = true)
	@NotNull(message = "请选择残疾类别")
	private Integer idtkind;
//	@ApiModelProperty(value = "评残照片", required = true)
//	@NotNull(message = "请选择评残照片")
//	private Blob picture;
}
