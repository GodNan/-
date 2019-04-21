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

@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CDPF_IQ", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfIq extends BaseEntity {

	private static final long serialVersionUID = -2510437503318870768L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId; // 残疾人主键

	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String requestId; // 请求主键
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer iqLevel; // 残疾等级
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer iqReason; // 致残主要原因
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer iqDegree1; // 发展商
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer iqDegree2; // 智商

	// 这个字段不知道有什么用
	// private Integer iqFunc;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer iqValue; // 智商值

	// 临时加
	// 现在有createDate/modifyDate
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date createtime;
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date edittime;

	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;
	// 这些字段暂时用不上
	// 操作人员信息通过请求表即可关联
	//
	// private String operid;
	//
	// private String opername;
	//
	// private Integer provinceId;
	//
	// private Integer updateFlag;

}