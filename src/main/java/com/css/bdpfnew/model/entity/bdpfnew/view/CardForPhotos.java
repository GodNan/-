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
 * @Date 2018年9月26日 下午3:51:16
 * @Description
 */

@Entity
@Table(name = "CARD_FOR_PHOTOS", schema = "bdpfnew2018")
@Data
public class CardForPhotos implements Serializable {

	private static final long serialVersionUID = 5433178051770599297L;
	@Id
	private String requestIdCard;
	private String citizenId;
	private String name;
	private String uuid;
	private String cityid;
	private String jiguanCode;
	private Integer cardBusiness;// 卡流程
	private Integer cardStatus;// 卡状态
	private Date modifyDate;
	private String remindText;

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
