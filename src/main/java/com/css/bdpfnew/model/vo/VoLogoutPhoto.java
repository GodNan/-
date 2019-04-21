/**
 * 
 */
package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年12月9日 下午2:18:09
 * @Description
 */
@Accessors
@Data
public class VoLogoutPhoto {
	private String citizenId;
	private String cdpfId;
	private String photo;
	private Integer delFlag;
}