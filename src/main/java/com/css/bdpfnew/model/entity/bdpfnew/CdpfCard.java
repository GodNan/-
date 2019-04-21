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
 * @Date 2018年7月23日 下午4:16:54
 * @Description:
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CDPF_CARD", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfCard extends BaseEntity {
	private static final long serialVersionUID = -5086175564042398257L;
	// 卡流程相关信息
	// 单独一个表保存 卡相关信息
	// cdpf_card

	// 这两个字段, 应该用不到了
	// private Integer cardFlow;// 卡流程当前状态
	// private Integer cardNextFlow;// 卡流程下一状态

	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String requestId;
	@Column(nullable = false, columnDefinition = "varchar2(20)")
	private String citizenId;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String name;// 姓名
	@Column(nullable = false, columnDefinition = "varchar2(12)")
	private String cityid;
	@Column(nullable = false, columnDefinition = "varchar2(50)")
	private String jiguanCode;// 户籍区划
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer cardBusiness;// 卡流程
	@Column(nullable = true, columnDefinition = "varchar2(20)")
	private String cardNo;// 卡号
	@Column(nullable = true, columnDefinition = "varchar2(20)")
	private String oldCardNo;// 旧卡号
	@Column(nullable = true, columnDefinition = "varchar2(20)")
	private String pcno;// 批次号
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer cardStatus;// 卡状态
	@Temporal(TemporalType.DATE)
	private Date givecarddate;// 发卡时间
	@Temporal(TemporalType.DATE)
	private Date firstgivecarddate;// 首次发卡时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date carddeadline;// 卡到期时间
	@Column(nullable = true, columnDefinition = "varchar2(10)")
	private String cardnoflag;// 卡标记位
	@Column(nullable = true, columnDefinition = "number(1)")
	private Integer isreportlosscard;// ???是否卡挂失
	@Temporal(TemporalType.TIMESTAMP)
	private Date reportlossdate;// ???卡挂失时间
	@Column(nullable = true, columnDefinition = "varchar2(26)")
	private String bjtNo;// 北京通号
	@Column(nullable = true, columnDefinition = "varchar2(260)")
	private String failReason;// ???制卡失败原因
	@Column(nullable = true, columnDefinition = "varchar2(20)")
	private String bankStatus;// 银行卡状态
	@Column(nullable = true, columnDefinition = "number(1)")
	private Integer resetType;// ???重制类型
	@Column(nullable = true, columnDefinition = "number(6)")
	private Integer resetState;// ???重制状态
	@Column(nullable = true, columnDefinition = "varchar2(20)")
	private String psno;// ???配送批次号
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer cardMakeState;// ???制卡状态

	// 银行卡信息
	@Column(nullable = true, columnDefinition = "varchar2(26)")
	private String bankNo;// 银行卡号
	@Temporal(TemporalType.TIMESTAMP)
	private Date bankactivatetime;// 银行卡激活时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date banklogofftime;// 银行卡挂失时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date bankcardtime;// ???银行卡更新时间
	@Column(nullable = true, columnDefinition = "varchar2(2)")
	private String losscard;// ???银行卡挂失状态
	@Column(nullable = true, columnDefinition = "varchar2(2)")
	private String releaselosscard;// ???银行卡解挂状态
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer hlSynced;// 状态是否已经同步到互联网,0:未同步;1:已同步
	@Column(nullable = true, columnDefinition = "varchar2(100)")
	private String remindText;//制卡照片核验不通过原因

}
