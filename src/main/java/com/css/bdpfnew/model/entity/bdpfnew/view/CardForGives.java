/**
 * 
 */
package com.css.bdpfnew.model.entity.bdpfnew.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * @Author erDuo
 * @Date 2018年10月4日 下午12:31:41
 * @Description
 */

@Entity
@Table(name = "CARD_FOR_GIVES", schema = "bdpfnew2018")
@Data
public class CardForGives implements Serializable {

	private static final long serialVersionUID = 5433178051770599297L;
	@Id
	private String uuid;
	private String requestId;
	private String cdpfId;
	private String citizenId;
	private String name;
	private String cityid;
	private String jiguanCode;
	private Integer cardBusiness;
	private Date modifyDate;

	@Transient
	public String getHandleDate() {
		if (!StringUtils.isEmpty(this.getModifyDate())) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(this.getModifyDate());
			return dateString;
		} else {
			return "";
		}

	}
}
