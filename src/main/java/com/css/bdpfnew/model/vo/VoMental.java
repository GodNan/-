package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午3:14:15
 * @Description:
 */
@Accessors
@Data
public class VoMental {

	private String uuid;
	private String cdpfId;

	private String requestId;
	private Integer mentalLevel;
	private Integer mentalReason;
	private Integer whoDasIi;

}
