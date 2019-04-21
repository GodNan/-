/**
 * 
 */
package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年9月21日 下午4:22:41
 * @Description
 */
@Accessors
@Data
public class VoCdpfCardLog {
	private String cdpfId;
	private String requestId;
	private String citizenId;
	private String name;// 姓名
	private Integer cardStatus;// 卡状态
	private String pcno;// 批次号
	private String logdata;
	private String opername;
}
