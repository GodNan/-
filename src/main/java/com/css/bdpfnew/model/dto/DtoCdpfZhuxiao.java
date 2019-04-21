/**
 * 
 */
package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年12月7日 下午5:35:23
 * @Description
 */
@ApiModel(value = "不注销操作所需参数", description = "不注销操作所需参数")
@Data
@Accessors(fluent = false)
public class DtoCdpfZhuxiao {

	@ApiModelProperty(value = "残疾人主键")
	private String cdpfId;
	@ApiModelProperty(value = "身份证号")
	private String citizenId;
	@ApiModelProperty(value = "注销状态")
	private Integer status; // 888:临时, 0:注销, 1:不注销
}
