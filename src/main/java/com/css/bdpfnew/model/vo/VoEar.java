package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午2:56:24
 * @Description:
 */
@Accessors
@Data
public class VoEar {

	private String cdpfId; // 残疾人主键

	private String requestId; // 请求主键

	private Integer earLevel;// 残疾等级
	private Integer earReason;// 致残主要原因

	private Integer earLeft1;

	private Integer earLeft2;

	private Integer earLeft3;

	private Integer earLeft4;

	private Integer earRight1;

	private Integer earRight2;

	private Integer earRight3;

	private Integer earRight4;

	private Integer noise;

	private Integer hearingLose;

	private Integer earSpeech;

}
