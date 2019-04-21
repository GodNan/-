package com.css.bdpfnew.model.entity.bdpfnew;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年6月14日 上午10:01:24
 * @Description: 任务请求表
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "REQUEST", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class Request extends BaseEntity {

	private static final long serialVersionUID = -4179840835275524397L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId; // 残疾人主键
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer processId;//流程id
	@Column(nullable = false)
	private String title;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String userId;// 初始操作人id
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String userName;// 初始操作人姓名
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String citizenName;// 残疾人姓名
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String citizenId;// 残疾人身份证号
	@Column(nullable = false, columnDefinition = "varchar2(12)")
	private String cityid;// 区划id
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer currentState;// 当前状态
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer formerState;// 前一状态
	
	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String optionsApplyFor;// 欲申请信息
	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String optionsAdopt;// 批准通过信息
	@Column(nullable = false, columnDefinition = "number(1) default 0", insertable = false)
	private Integer hlSynced; //互联网同步标志
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;

}
