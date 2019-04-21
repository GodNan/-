package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午3:11:22
 * @Description:
 */
@Accessors
@Data
public class VoIq {

	private String uuid;
	private String cdpfId;

	private String requestId;
	private Integer iqLevel;
	private Integer iqReason;
	private Integer iqDegree1;
	private Integer iqDegree2;
	private Integer iqValue;

}
