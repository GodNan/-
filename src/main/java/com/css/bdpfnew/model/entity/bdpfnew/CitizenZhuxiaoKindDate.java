/**
 * 
 */
package com.css.bdpfnew.model.entity.bdpfnew;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2019年3月7日 上午10:52:56
 * @Description
 */

@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CITIZEN_ZHUXIAO_KIND_DATE", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CitizenZhuxiaoKindDate extends BaseEntity {

	private static final long serialVersionUID = 5030100916634075364L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId;
	@Column(nullable = false, columnDefinition = "varchar2(20)")
	private String citizenId;
	@Column(nullable = false, columnDefinition = "number(4) default 0")
	private Integer idtKind;
	@Column(nullable = false, columnDefinition = "number(4) default 0")
	private Integer zhuxiaoType; //8: 残疾人已注销; 1: 只注销了对应类别 ; 0: 未注销	
	@Column(nullable = false, columnDefinition = "number(4) default 0")
	private Integer hlSynced;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date nextApplyDate;// 下次申请日期

	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;
}