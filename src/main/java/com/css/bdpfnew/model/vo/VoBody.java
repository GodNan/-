package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午3:07:09
 * @Description:
 */
@Accessors
@Data
public class VoBody {

	private String uuid;
	private String cdpfId;

	private String requestId;
	private Integer bodyLevel;
	private Integer bodyReason;
	private Integer bodyDetail;

}
