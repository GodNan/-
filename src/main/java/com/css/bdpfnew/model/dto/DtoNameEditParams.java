package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author HEYCH
 * @Date 2019年1月27日
 * @Description:
 */
@ApiModel(value = "修改姓名参数", description = "修改姓名参数")
@Data
@Accessors(fluent = false)
public class DtoNameEditParams {
	private String cdpfId;
	private String newname;
}
