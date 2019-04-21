/**
 * 
 */
package com.css.bdpfnew.model.entity.bdpfnew;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年9月21日 上午11:13:30
 * @Description 制卡日志
 */

@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CDPF_CARD_LOG", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfCardLog extends BaseEntity {

	private static final long serialVersionUID = -1483088574606988173L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String requestId;
	@Column(nullable = false, columnDefinition = "varchar2(20)")
	private String citizenId;
//	@Column(nullable = false, columnDefinition = "varchar2(32)")
//	private String name;// 姓名
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer cardStatus;// 卡状态
	@Column(nullable = true, columnDefinition = "varchar2(20)")
	private String pcno;// 批次号
	@Column(nullable = false, columnDefinition = "varchar2(100)")
	private String logdata;
	@Column(nullable = false, columnDefinition = "varchar2(30)")
	private String opername;
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;

	@Transient
	public String getHandleDate() {
		if (!StringUtils.isEmpty(super.getModifyDate())) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(super.getModifyDate());
			return dateString;
		} else {
			return "";
		}
	}

}
