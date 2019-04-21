/**
 * 
 */
package com.css.bdpfnew.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年9月19日 下午4:38:27
 * @Description
 */
@Accessors
@Data
public class VoCdpfCard {
	// 这两个字段, 应该用不到了
	private Integer cardFlow;// 卡流程当前状态
	private Integer cardNextFlow;// 卡流程下一状态

	private String cdpfId;
	private String requestId;
	private String citizenId;
	private String name;
	private String cityid;
	private Integer cardBusiness;// 卡流程
	private String cardNo;// 卡号
	private String oldCardNo;// 旧卡号
	private String pcno;// 批次号
	private Integer cardStatus;// 卡状态
	private Date givecarddate;// 发卡时间
	private Date firstgivecarddate;// 首次发卡时间
	private Date carddeadline;// 卡到期时间
	private String cardnoflag;// 卡标记位
	private Integer isreportlosscard;// ???是否卡挂失
	private Date reportlossdate;// ???卡挂失时间
	private String bjtNo;// 北京通号
	private String failReason;// ???制卡失败原因
	private String bankStatus;// 银行卡状态
	private Integer resetType;// ???重制类型
	private Integer resetState;// ???重制状态
	private String psno;// ???配送批次号
	private Integer cardMakeState;// ???制卡状态

	// 银行卡信息
	private String bankNo;// 银行卡号
	private Date bankactivatetime;// 银行卡激活时间
	private Date banklogofftime;// 银行卡挂失时间
	private Date bankcardtime;// ???银行卡更新时间
	private String losscard;// ???银行卡挂失状态
	private String releaselosscard;// ???银行卡解挂状态
}
