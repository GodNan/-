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
@Table(name = "CDPF_EAR", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfEar extends BaseEntity {
	private static final long serialVersionUID = -941967103839593789L;

	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId; // 残疾人主键

	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String requestId; // 请求主键

	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer earLevel;// 残疾等级
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer earReason;// 致残主要原因
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer earLeft1;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer earLeft2;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer earLeft3;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer earLeft4;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer earRight1;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer earRight2;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer earRight3;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer earRight4;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer noise;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer hearingLose;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer earSpeech;

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