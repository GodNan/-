package com.css.bdpfnew.model.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author HEYCH
 * @Date 2019年2月26日
 * @Description:
 */
@ApiModel(value = "修改身份证号参数", description = "修改身份证号参数")
@Data
@Accessors(fluent = false)
public class DtoCitizenIdEditParams {
	private String cdpfId;
	private String newCitizenId;
	@Temporal(TemporalType.DATE)
	private Date newBirthdate;
}
