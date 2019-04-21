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
 * @Date 2018年10月22日 下午2:56:56
 * @Description
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CDPF_CITIZEN_NET", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfCitizenNet extends BaseEntityUuidAssigned {

	private static final long serialVersionUID = 3454448336251155038L;
	// 互联网相关字段
	@Column(nullable = true, columnDefinition = "varchar2(100)")
	private String hlReviewPerson;
	@Column(nullable = true, columnDefinition = "varchar2(100)")
	private String hlShouliPerson;
	@Column(nullable = true, columnDefinition = "varchar2(100)")
	private String hlReviewComment;
	@Column(nullable = true, columnDefinition = "varchar2(100)")
	private String hlShouliComment;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer isGongan;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer isZclrkk;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer isZhengwu;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer reviewStatus;

	// 临时加
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date edittime;

	// 基本信息
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String citizenId;// 身份证号
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String name;// 姓名
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer gender;// 性别
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer race;// 名族
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer marriager;// 婚姻状况
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthdate;// 出生日期
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer eduLevel;// 教育程度
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer political;// 政治面貌
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer hukouKind;// 户口类别
	@Column(columnDefinition = "varchar2(32)")
	private String email;// 邮箱
	@Column(nullable = true, columnDefinition = "varchar2(11)")
	private String mobilePhone;// 移动电话
	@Column(nullable = true, columnDefinition = "varchar2(15)")
	private String phoneNo;// 电话

	// 户籍地居住地信息
	// 区划 是 Long 还是 String
	@Column(nullable = false)
	private String cityid;// 户籍区划id
	@Column(nullable = false)
	private String jiguanCode;// 户籍区划
	@Column(nullable = false)
	private String zipcode;// 户籍邮编
	@Column(nullable = false)
	private String cityidAddrstr;// 户籍地详细地址
	@Column(nullable = false)
	private String residentCity;// 居住区划id
	@Column(nullable = false)
	private String residentAdd;// 居住区划
	@Column(nullable = false)
	private String residentZipcode;// 居住邮编
	@Column(nullable = false)
	private String residentcityAddrstr;// 居住地详细地址

	// 联系人信息
	@Column(nullable = false)
	private String guardian;// 联系人姓名
	@Column(nullable = false)
	private String guardianCitizenId;// 联系人身份证号
	@Column(nullable = true)
	private String guardianPhone;// 联系人电话
	@Column(nullable = true, name = "guardian_m_phone")
	private String guardianMobilePhone;// 联系人手机
	@Column(nullable = false)
	private Integer guardianGender;// 联系人性别
	@Column(nullable = false)
	private Integer guardianOrAgent;// 联系人类型
	@Column(nullable = false, name = "guardian_r")
	private Integer guardianRelation;// 联系人关系

	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String requestId;// 任务主键

	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String requestIdFinished;// 任务主键

	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String requestIdCard;// 任务主键

	// 证流程信息
	private Integer businessId;// 对应 现在的 processId 流程
	// TODO: 此字段,已经不需要实时同步证的状态,暂时用来 标记 已发证 已注销 等
	private Integer statusRecord;// 证状态
	// TODO: 新增字段, 标记残疾人已发过残疾卡
	@Column(nullable = false, columnDefinition = "number(1) default 0", insertable = false)
	private Integer hadGaveCard;//
	@Temporal(TemporalType.DATE)
	private Date certDate;// 发证(残疾认定)日期
	@Temporal(TemporalType.DATE)
	private Date firstCertDate;// 首次发证(残疾认定)日期
	private String cardNum;// 残疾证号
	private String firstcardnum;// 首次残疾证号
	@Temporal(TemporalType.TIMESTAMP)
	private Date zhuxiaoTime;// 首次发证(残疾认定)日期
	private Integer isMoving;// 是否迁移中

	private String photoid;// 照片关联id

	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;

	@Column(nullable = false, columnDefinition = "number(4) default 2", insertable = false)
	private Integer idtAtHome;

	@Column(nullable = false, columnDefinition = "number(1) default 0", insertable = false)
	private Integer hlSynced; // 互联网同步标志

	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String cardNo;// 卡号
	private Integer cardStatus;// 卡状态

	private Integer dongjieMark;// ???冻结标记/解冻/自动注销
	@Temporal(TemporalType.TIMESTAMP)
	private Date dongjietime;// ???冻结/解冻/自动注销时间

}
