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
 * @Author HEYCH
 * @Date 2018年11月13日 上午10:41:19
 * @Description:
 */
@Entity
@Table(name = "CITIZEN_FOR_ZHUXIAOS", schema = "bdpfnew2018")
@Data
public class CitizenForZhuxiaos implements Serializable {
	private static final long serialVersionUID = 1191335784798702076L;
	@Id
	private String uuid;
	private String requestId;
	private String requestIdFinished;
	private Integer hadFinalReviewed;
	private String citizenId;
	private String name;
	private String cityid;
	private String jiguanCode;
	private Date modifyDate;
	private Integer zxSource;
	private Date deathDate;
	private Date filloutDate;

	/**
	 * 获取修改日期
	 *
	 * @return 修改日期
	 */
	@Transient
	public String getModifyDateFormat() {
		if (!StringUtils.isEmpty(modifyDate)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(modifyDate);
			return dateString;
		} else {
			return "";
		}
	}

	@Transient
	public String getDeathDateFormat() {
		if (!StringUtils.isEmpty(deathDate)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(deathDate);
			return dateString;
		} else {
			return "";
		}
	}

	@Transient
	public String getFilloutDateFormat() {
		if (!StringUtils.isEmpty(filloutDate)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(filloutDate);
			return dateString;
		} else {
			return "";
		}
	}

}
