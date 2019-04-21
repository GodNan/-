package com.css.bdpfnew.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author HEYCH
 * @Date 2018年12月12日 上午9:44:03
 * @Description:
 */
@ApiModel(value = "批量接收残疾人所需参数", description = "批量接收残疾人所需参数")
@Data
@Accessors(fluent = false)
public class DtoBatchAreaAccept {
	List<String> cdpfIds;
}
