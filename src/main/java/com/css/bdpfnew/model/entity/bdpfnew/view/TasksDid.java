package com.css.bdpfnew.model.entity.bdpfnew.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * @Author erDuo
 * @Date 2018年6月19日 下午4:46:09
 * @Description:
 */
@Entity
@Table(name = "TASKS_DID", schema = "bdpfnew2018")
@Data
public class TasksDid {

	@Id
	private String uuid;
	private String cdpfId;
	private String citizenId;
	private String citizenName;
	private Integer processId;
	private Integer currentState;
	private Integer handleState;
	private Date modifyDate;
	private Date createDate;
	private String userId;
	private String cityid;
	private String optionsApplyFor;// 欲评残类别
	private String optionsAdopt;

	/**
	 * 获取创建日期
	 *
	 * @return 创建日期
	 */
	@Transient
	public String getCreateDateFormat() {
		if (StringUtils.isEmpty(createDate)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(createDate);
			return dateString;
		} else {
			return "";
		}
	}

	/**
	 * 获取修改日期
	 *
	 * @return 修改日期
	 */
	@Transient
	public String getModifyDateFormat() {
		if (StringUtils.isEmpty(modifyDate)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(modifyDate);
			return dateString;
		} else {
			return "";
		}
	}

}
