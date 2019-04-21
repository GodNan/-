package com.css.bdpfnew.model.entity.bdpfnew;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author HEYCH
 * @Date 2018年12月14日 下午4:16:54
 * @Description:
 */
@Entity
@Table(name = "CDPF_MIDDLE_AREA", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfMiddleArea extends BaseEntity {
	private static final long serialVersionUID = 4041860958106317696L;
//	private String uuid;
	@Column(nullable = false, columnDefinition = "varchar2(12)")
	private String preArea;
	@Column(nullable = false, columnDefinition = "varchar2(12)")
	private String nextArea;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer flowId;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer nextflowId;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer status;
	@Column(nullable = false, columnDefinition = "varchar2(60)")
	private String cdpfId; //对应旧库的citizenId
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String personalId; // 身份证号码
	@Column(nullable = false, columnDefinition = "varchar2(500)")
	private String preName;
	@Column(nullable = false, columnDefinition = "varchar2(500)")
	private String nextName;
	@Column(nullable = false, columnDefinition = "varchar2(40)")
	private String pname;
	@Temporal(TemporalType.DATE)
	private Date preTime;
	@Temporal(TemporalType.DATE)
	private Date nextTime;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer hlSynced;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer isHulian;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer updateFlag;

}
