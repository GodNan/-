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

import com.css.bdpfnew.util.DateUtil;

import lombok.Data;

/**
 * @Author erDuo
 * @Date 2018年9月28日 上午10:00:51
 * @Description
 */
@Entity
@Table(name = "CARD_FOR_CHANGES", schema = "bdpfnew2018")
@Data
public class CardForChanges implements Serializable {

	private static final long serialVersionUID = 5433178051770599297L;
	@Id
	private String uuid;
	private String citizenId;
	private String name;
	private String cityid;
	private String jiguanCode;
	private Date reportlossdate;
	private Integer idtLevel;
	private Integer idtKind;
	private String beizhu;

	@Transient
	public Long getReportlossDays() {
		if (!StringUtils.isEmpty(this.getReportlossdate())) {
			return DateUtil.daysBetween(new Date(), this.getReportlossdate());
		} else {
			return 0L;
		}
	}

}