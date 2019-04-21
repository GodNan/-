package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年7月6日 上午10:33:30
 * @Description:
 */
@Accessors
@Data
public class VoRequestNote {
	private String requestId;
	private String userId;
	private String userName;
	private String note;
	private Integer handleState;
	private Integer finishState;
	private Integer handleType;

}
