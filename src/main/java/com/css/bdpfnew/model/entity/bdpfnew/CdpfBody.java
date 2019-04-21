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
@Table(name = "CDPF_BODY", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfBody extends BaseEntity {

	private static final long serialVersionUID = -7062136859503117288L;

	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId; // 残疾人主键

	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String requestId; // 请求主键
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer bodyLevel; // 残疾等级
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer bodyReason;// 残疾原因
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer bodyDetail;// 残疾情况

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