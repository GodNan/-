/**
 * 
 */
package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年11月5日 上午10:56:21
 * @Description
 */

@Accessors
@Data
public class VoPost {

	private String cdpfId;// 残疾人id
	private String requestId;// 请求id
	private String postUserId;// 经办人id
	private String postUserName;// 经办人姓名
	private String postManagerId;// 理事长id
	private String postManagerName;// 理事长姓名
	private String note;// 公示问题
	private Integer postType; // 公示类型

}