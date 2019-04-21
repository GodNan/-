/**
 * 
 */
package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年10月29日 上午10:40:59
 * @Description
 */
@Accessors
@Data
public class VoIdtPhoto {
	private String citizenId;
	private String idtRecordUuid;
	private Integer flag;
	private Integer idtkind;
	private String photo;
	private Integer delFlag;
}
