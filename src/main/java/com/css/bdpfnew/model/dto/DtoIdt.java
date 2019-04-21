package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:45:46
 * @Description:
 */
@ApiModel(value = "残疾信息新增所需参数", description = "残疾评定受理残疾参数")
@Data
@Accessors(fluent = false)
public class DtoIdt {
	private Integer min;
	private String optionsApplyFor;
	private DtoCitizen dtocitizen;
	private DtoIdtResult dtoresultX;
	private DtoEye dtoeye;
	private DtoEar dtoear;
	private DtoSpeech dtospeech;
	private DtoBody dtobody;
	private DtoIq dtoiq;
	private DtoMental dtomental;

}
