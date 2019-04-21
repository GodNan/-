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
 * @Date 2018年12月10日 下午4:01:09
 * @Description
 */
@ApiModel(value = "卡面照片标记更新参数", description = "卡面照片标记更新参数")
@Data
@Accessors(fluent = false)
public class DtoCardPhotoParams {
	@ApiModelProperty(value = "残疾人主键", required = true)
	@NotNull(message = "请选择残疾人")
	private String cdpfId;
}