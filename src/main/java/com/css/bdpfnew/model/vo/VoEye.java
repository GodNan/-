package com.css.bdpfnew.model.vo;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午2:51:05
 * @Description:
 */
@Accessors
@Data
public class VoEye {

	private String uuid;
	private String cdpfId;

	private String requestId;
	private Integer eyeLevel;
	private Integer eyeReason;
	private BigDecimal sightLeft;
	private BigDecimal sightRight;
	private Integer visionLeft;
	private Integer visionRight;

}
