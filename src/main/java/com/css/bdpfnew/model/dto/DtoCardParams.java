package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author HEYCH
 * @Date 2018年10月30日 下午2:52:44
 * @Description:
 */
@ApiModel(value = "发卡所需参数", description = "发卡所需参数")
@Data
@Accessors(fluent = false)
public class DtoCardParams {
	private String sInfoData;
	private String sYearInfo;
	private String sLossData;
	private String sReleaseData;
	private String sNewCardInfo;
	private String sNewInfo;

}
