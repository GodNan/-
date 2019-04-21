package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author HEYCH
 * @Date 2018年10月28日
 * @Description:
 */
@ApiModel(value = "发卡所需参数", description = "发卡所需参数")
@Data
@Accessors(fluent = false)
public class DtoCardGiveParams {
	private String requestIdCard;
	private Integer check260;
	private String cardNoFlagT;
	private String newCardNo;
}
