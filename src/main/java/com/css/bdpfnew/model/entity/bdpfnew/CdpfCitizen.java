package com.css.bdpfnew.model.entity.bdpfnew;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CDPF_CITIZEN", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfCitizen extends BaseEntityUuidAssigned {

	private static final long serialVersionUID = 2207673099552851752L;

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
	@Column(columnDefinition = "varchar2(50)")
	private String email;// 邮箱
	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String mobilePhone;// 移动电话
	@Column(nullable = true, columnDefinition = "varchar2(50)")
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

	// gis信息
	private String cityidAddrstate;
	private String cityidX;
	private String cityidY;
	private String cityidAddrmatchstr;
	private String residentcityAddrstate;
	private String residentcityX;
	private String residentcityY;
	private String residentcityAddrmatchstr;

	// 联系人信息
	@Column(nullable = false)
	private String guardian;// 联系人姓名
	@Column(nullable = false)
	private String guardianCitizenId;// 联系人身份证号
	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String guardianPhone;// 联系人电话
	@Column(nullable = true, name = "guardian_m_phone", columnDefinition = "varchar2(50)")
	private String guardianMobilePhone;// 联系人手机
	
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer guardianGender;// 联系人性别
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer guardianOrAgent;// 联系人类型
	@Column(nullable = false, columnDefinition = "number(4)", name = "guardian_r")
	private Integer guardianRelation;// 联系人关系

	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String requestId;// 任务主键

	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String requestIdFinished;// 任务主键

	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String requestIdCard;// 任务主键

	// 证流程信息
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer businessId;// 对应 现在的 processId 流程
	// TODO: 此字段,已经不需要实时同步证的状态,暂时用来 标记 已发证 已注销 等
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer statusRecord;// 证状态
	// TODO: 新增字段, 标记残疾人已发过残疾卡
	@Column(nullable = false, columnDefinition = "number(1) default 0", insertable = false)
	private Integer hadGaveCard;//
	// 已批准
	@Column(nullable = false, columnDefinition = "number(2) default 0", insertable = false)
	private Integer hadFinalReviewed;//
	// 是否需要一个 认证残疾人的字段, 当前 statusRecord 只有两个值有意义(9: 已认定, 8: 已注销,
	// 应该只有认定过的残疾人才可以注销)
	@Temporal(TemporalType.DATE)
	private Date certDate;// 发证(残疾认定)日期
	@Temporal(TemporalType.DATE)
	private Date firstCertDate;// 首次发证(残疾认定)日期
	private String cardNum;// 残疾证号
	private String firstcardnum;// 首次残疾证号
	@Temporal(TemporalType.TIMESTAMP)
	private Date zhuxiaoTime;// 首次发证(残疾认定)日期
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer isMoving;// 是否迁移中

	// 这两个字段应该用不到了
	private Integer flowId;// 当前证的流程节点,可以对应现在设计的 任务状态
	private Integer nextflowId;// 证的下一个流程节点

	// 这部分不确定
	private String certName;// 发证人???此字段还需要吗
	private Integer newType;// ???证件申请类型
	private Integer statusCard;// ???发卡状态
	private Integer statusPerson;// ???发证状态

	// 残疾信息
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer idtLevel;// 评残等级
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer idtKind;// 评残类别
	private String kindstr;// 具体类别
	private String levelstr;// 具体类别等级

	// 修改后原数据
	// 旧姓名/身份证号,制卡视图需要
	private String oldname;// 旧姓名
	private String oldCitizenId;// 旧身份证号
	private String oldkindstr;// 旧残疾类型 - 涉及到视力残疾类别新增或删除时候的判断,需要进行卡残损换新

	private String photoid;// 照片关联id

	// ???生僻字
	private String obscure;// 制卡生僻字姓名
	private String obscurecerted;// 发证姓名生僻字???是不是以后用不到了

	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;
	
	@Column(nullable = false, columnDefinition = "number(4) default 2", insertable = false)
	private Integer idtAtHome;

	// ---------------------------------------------------------------------------------------------------

	// 评残的一系列数据
	// 可以提出一个表 cdpf_idt 保存评残信息
	// 通过request_id确定, 每次评残 都新建一条数据
	// citizen_idt_photos 保存评定照片
	// private String idtComment;// 评残内容
	// private String idtName;// 评残医生
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date idtDate;// 评残日期
	// private String idtHospital;// 评残医院
	// private Integer idtType;// ???评定方式
	// private String memo;// ???备注（评定结果）

	// ---------------------------------------------------------------------------------------------------

	// 卡流程相关信息
	// 单独一个表保存 卡相关信息
	// cdpf_card
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer cardBusiness;// 卡流程
	// private Integer cardFlow;// 卡流程当前状态
	// private Integer cardNextFlow;// 卡流程下一状态
	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String cardNo;// 卡号
	// private String oldCardNo;// 旧卡号
	// private String pcno;// 批次号
	// TODO: 卡状态,暂时放开 -- 需要实时同步 北控的 卡状态
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer cardStatus;// 卡状态
	// @Temporal(TemporalType.DATE)
	// private Date givecarddate;// 发卡时间
	@Temporal(TemporalType.DATE)
	private Date firstgivecarddate;// 首次发卡时间
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date carddeadline;// 卡到期时间
	// private String cardnoflag;// 卡标记位
	private Integer isreportlosscard;// ???是否卡挂失
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date reportlossdate;// ???卡挂失时间
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date cardedittime;// 卡编辑时间
	// @Column(nullable = true, columnDefinition = "varchar2(26)")
	// private String bjtNo;// 北京通号
	// private String failReason;// ???制卡失败原因
	// private String bankStatus;// 银行卡状态
	// private Integer resetType;// ???重制类型
	// private Integer resetState;// ???重制状态
	// private String psno;// ???配送批次号
	// private Integer cardMakeState;// ???制卡状态

	// 银行卡信息
	@Transient
	private String bankNo;// 银行卡号
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date bankactivatetime;// 银行卡激活时间
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date banklogofftime;// 银行卡挂失时间
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date bankcardtime;// ???银行卡更新时间
	// private String losscard;// ???银行卡挂失状态
	// private String releaselosscard;// ???银行卡解挂状态

	// ---------------------------------------------------------------------------------------------------

	// 互联网新增字段
	@Column(nullable = false, columnDefinition = "number(4) default 1")
	private Integer isHulian;// 是否互联
	@Column(nullable = false, columnDefinition = "number(4) default 1")
	private Integer hadIdtPhoto;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer hlSynced;// 证状态是否已经同步到互联网,0:未同步;1:已同步
	private String idtRecordUuid;// 评残记录关联id
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer cardHlSynced;// 残疾人(卡)状态是否已经同步到互联网,0:未同步;1:已同步
	private Integer updateFlag;// ???互联网,政务网同步字段
	// private Integer reviewFlag;// 复评???待开发,或许以后用不到了
	private String idtRecordUuidReview;// 复评记录关联字段???待开发,或许以后用不到了

	// 标志位
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer isbanka;// ???是否办卡
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer isokCardphoto;// ???制卡照片是否合格
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer iecardfacebackFlag;// ???卡面变更标记字段
	private String ideamemo;// ???注销原因 - 存的什么值

	// 录入人信息 - 保留
	private String operid;// ???录入人ID - 或许也用不到
	private String opername;// ???录入人姓名 - 或许也用不到

	@Column(nullable = false, columnDefinition = "number(1) default 0", insertable = false)
	private Integer waitforcardchange;// 待补换卡???
	@Column(nullable = false, columnDefinition = "number(1) default 0", insertable = false)
	private Integer photoPushState;// ???制卡图片推送状态
	// private Integer zxSource;// ???注销来源

	private Integer dongjieMark;// ???冻结标记/解冻/自动注销
	@Temporal(TemporalType.TIMESTAMP)
	private Date dongjietime;// ???冻结/解冻/自动注销时间
	
	@Column(nullable = true)
	private String oldcityidKsqc;// 跨省迁出原户籍地
	
	@Column(nullable = true, columnDefinition = "number(10)")
	private Integer logoutReason;// 注销原因
	

	// ???未确定字段
//	private String cjqkDescribe;// 残疾情况描述 - 在哪用
//	private Integer dataFrom;// ???录入方式 - 是否用到
//	// 应该是同一个意思,在哪用到???
//	@Temporal(TemporalType.DATE)
//	private Date disableDate;// ???致残时间
//	@Temporal(TemporalType.DATE)
//	private Date disdate;// ???致残日期
//	private Integer flag;// ???没用到
//	private String familyno;// ???
//	private String gauuid;// ???
//	private Integer gastatus;// ???
//	private String ifNo;// ???
//	private Integer severeDisability;// ???是否重残
//	private Integer parentcode;// ???上一级城市 - 是否用到
//	private Integer phonetype;// ???电话归属（？？？）好像已经不用了，默认值：1
//	private Integer statusIdcard;// ???
//	private Integer syncflag;// ???什么的同步标志
//	private String workUnit;// ???备注
//	private String workKind;// ???档案编号
//	private Integer workUnitP;// ???单位性质
//	private Integer isfuli;// ???是否福利企业
//	private Integer isshangjiao;// ???是否上交卡 - 未用到
//	private Integer isshoufei;// ???是否收费 - 未用到
//	private String regAddr;// ???原户籍地址
//	private String habAddr;// ???原居住地地址
//	private Integer provinceId;// ???
//	private String nowAdd;// ???现住址 好像已经不用了
}