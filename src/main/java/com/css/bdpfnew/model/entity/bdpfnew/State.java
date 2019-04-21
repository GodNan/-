package com.css.bdpfnew.model.entity.bdpfnew;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年7月3日 下午3:52:20
 * @Description:
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "STATE", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class State extends BaseEntity {
	private static final long serialVersionUID = 8070573148291956404L;

	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer stateCode;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer stateType;

	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer processId;// 流程id

	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String name;// 状态名
	@Column(nullable = false, columnDefinition = "varchar2(150)")
	private String detail;// 状态描述

	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer orderNum;
	@Column(nullable = false, columnDefinition = "varchar2(50)")
	private String icon;// 状态icon
	
	@Column(nullable = false, columnDefinition = "varchar2(100)")
	private String permissionId;// 权限

	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;
}
