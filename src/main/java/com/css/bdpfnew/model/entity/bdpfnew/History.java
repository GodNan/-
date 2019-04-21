package com.css.bdpfnew.model.entity.bdpfnew;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年6月14日 上午10:28:08
 * @Description: 历史 (任务/流程) 表
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "HISTORY", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class History extends BaseEntity {

	private static final long serialVersionUID = -2195791968090700971L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId; // 残疾人主键
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer processId;// 流程id
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String requestId;// 请求id
	@Column(nullable = false)
	private String title;// 可能用不上
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String citizenName;// 残疾人姓名
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String citizenId;// 残疾人身份证号
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String userId;// 初始操作人id
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String userName;// 初始操作人姓名
	@Column(nullable = false, columnDefinition = "varchar2(12)")
	private String cityid;// 区划id
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer currentState;// 任务当前状态
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer formerState;// 任务前一状态
	
	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String optionsApplyFor;// 欲评残类别
	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String optionsAdopt;// 通过评残类别
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date requestCreateDate;// 请求申请时间

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date requestModifyDate;// 请求完成时间

	/**
	 * 获取任务创建日期
	 *
	 * @return 创建日期
	 */
	@Transient
	public String getRequestCreateDateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(requestCreateDate);
		return dateString;
	}

	/**
	 * 获取任务修改日期
	 *
	 * @return 修改日期
	 */
	@Transient
	public String getRequestModifyDateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(requestModifyDate);
		return dateString;
	}

}
