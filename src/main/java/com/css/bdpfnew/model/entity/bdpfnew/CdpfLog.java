package com.css.bdpfnew.model.entity.bdpfnew;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年8月21日 上午10:01:59
 * @Description:
 */
@Entity
@Table(name = "CDPF_LOG", schema = "bdpfnew")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfLog extends BaseEntity {

	private static final long serialVersionUID = 1567457203733253215L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId;
	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String requestId;
	@Column(nullable = false, columnDefinition = "varchar2(4000)")
	private String logData;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer logType;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer opertype;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String operid;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String opername;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer updateFlag;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date edittime;

	@Transient
	public String getHandleDate() {
		String dateString = "";
		if (!StringUtils.isEmpty(this.getEdittime())) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateString = formatter.format(this.getEdittime());
		}
		return dateString;
	}

}
