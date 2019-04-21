package com.css.bdpfnew.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年8月7日 上午9:44:03
 * @Description:
 */
@ApiModel(value = "残疾人户籍迁移申请所需参数", description = "残疾人户籍迁移申请提交")
@Data
@Accessors(fluent = false)
public class DtoAreaMove {

	@ApiModelProperty(value = "残疾人主键", required = true)
	private String uuid;

	@ApiModelProperty(value = "姓名", required = true)
	@NotNull(message = "请填写姓名")
	private String name;
	@ApiModelProperty(value = "身份证号", required = true)
	@NotNull(message = "请填写身份证号")
	private String citizenId;
	@ApiModelProperty(value = "户籍区划id", required = true)
	@NotNull(message = "请选择户籍区划")
	private String cityid;
	@ApiModelProperty(value = "目标户籍区划id", required = true)
	@NotNull(message = "请选择目标户籍区划")
	private String newCityid;

}
