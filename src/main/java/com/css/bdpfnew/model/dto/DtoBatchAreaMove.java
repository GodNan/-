package com.css.bdpfnew.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author HEYCH
 * @Date 2018年12月12日 上午9:44:03
 * @Description:
 */
@ApiModel(value = "批量残疾人户籍迁移申请所需参数", description = "批量残疾人户籍迁移申请提交")
@Data
@Accessors(fluent = false)
public class DtoBatchAreaMove {

	@NotNull(message = "请填写身份证号")
	private List<String> citizenIds;
	@ApiModelProperty(value = "目标户籍区划id", required = true)
	@NotNull(message = "请选择目标户籍区划")
	private String newCityid;

}
