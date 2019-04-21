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
 * @Date 2018年7月23日 下午4:13:43
 * @Description:
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CDPF_IDT", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class IdtResult extends BaseEntity {
	private static final long serialVersionUID = -2837861043854962967L;

	// 评残的一系列数据
	// 可以提出一个表 cdpf_idt 保存评残信息
	// 通过request_id确定, 每次评残 都新建一条数据
	// citizen_idt_photos 保存评定照片
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId;// 残疾人主键id
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String requestId;// 请求id
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer idtLevel;// 评残等级
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer idtKind;// 评残类别
	@Column(nullable = false, columnDefinition = "varchar2(15)")
	private String kindstr;// 具体类别
	@Column(nullable = true, columnDefinition = "varchar2(15)")
	private String oldkindstr;// 原评残类别
	@Column(nullable = false, columnDefinition = "varchar2(30)")
	private String levelstr;// 具体类别等级
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer newType;// ???证件申请类型
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer idtType;// ???评定方式
	@Column(nullable = true, columnDefinition = "varchar2(200)")
	private String idtHospital;// 评残医院
	@Column(nullable = false, columnDefinition = "varchar2(100)")
	private String idtName;// 评残医生
	@Column(nullable = true, columnDefinition = "varchar2(300)")
	private String idtComment;// 处理意见
	@Temporal(TemporalType.TIMESTAMP)
	private Date idtDate;// 评残日期
	@Column(nullable = true, columnDefinition = "varchar2(200)")
	private String cjqkDescribe;// 残疾情况描述
	@Column(nullable = true, columnDefinition = "varchar2(200)")
	private String memo;// ???备注（评定结果）
	@Column(nullable = false, columnDefinition = "number(1) default 0", insertable = false)
	private Integer hlSynced; //互联网同步标志
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;
	
	

}
