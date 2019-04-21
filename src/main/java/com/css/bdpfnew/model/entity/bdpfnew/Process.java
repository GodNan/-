package com.css.bdpfnew.model.entity.bdpfnew;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年6月14日 上午10:24:03
 * @Description: 工作流程表
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "PROCESS", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class Process extends BaseEntity {

	private static final long serialVersionUID = 7570550880321305633L;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer processId;// 流程id
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String name;// 流程名
	@Column(nullable = false, columnDefinition = "varchar2(150)")
	private String detail;// 流程描述
	@Column(nullable = false, columnDefinition = "varchar2(255)")
	private String flow;// 流程顺序字符串
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;
	@Column(nullable = false, columnDefinition = "number(4) default 1")
	private Integer type;
}
