/**
 * 
 */
package com.css.bdpfnew.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年9月19日 下午4:24:42
 * @Description
 */
@ApiModel(value = "残疾人制卡数据所需参数", description = "卡申请提交")
@Data
@Accessors(fluent = false)
public class DtoCdpfCard {
	// 这两个字段, 应该用不到了
	private Integer cardFlow;// 卡流程当前状态
	private Integer cardNextFlow;// 卡流程下一状态
	
	@ApiModelProperty(value = "残疾人主键", required = true)
	@NotNull(message = "请选择残疾人")
	private String cdpfId;
	@ApiModelProperty(value = "任务主键", required = true)
	@NotNull(message = "请选择任务")
	private String requestId;
	@ApiModelProperty(value = "残疾人身份证号", required = true)
	@NotNull(message = "请选择残疾人")
	private String citizenId;
	@ApiModelProperty(value = "残疾人姓名", required = true)
	@NotNull(message = "请选择残疾人")
	private String name;// 姓名
	@ApiModelProperty(value = "残疾人区划", required = true)
	@NotNull(message = "请选择残疾人区划")
	private String cityid;
	@ApiModelProperty(value = "卡流程", required = true)
	@NotNull(message = "请选择卡流程")
	private Integer cardBusiness;// 卡流程
	@ApiModelProperty(value = "残疾人卡号")
	private String cardNo;// 卡号
	@ApiModelProperty(value = "残疾人旧卡号")
	private String oldCardNo;// 旧卡号
	@ApiModelProperty(value = "批次号")
	private String pcno;// 批次号
	@ApiModelProperty(value = "卡状态")
	private Integer cardStatus;// 卡状态
	@ApiModelProperty(value = "发卡时间")
	private Date givecarddate;// 发卡时间
	// TODO: 首次发卡时间 应该 在 主表 里
	@ApiModelProperty(value = "首次发卡时间")
	private Date firstgivecarddate;// 首次发卡时间
	@ApiModelProperty(value = "卡到期时间")
	private Date carddeadline;// 卡到期时间
	@ApiModelProperty(value = "卡标记位")
	private String cardnoflag;// 卡标记位
	@ApiModelProperty(value = "是否挂失卡")
	private Integer isreportlosscard;// ???是否卡挂失
	@ApiModelProperty(value = "卡挂失时间")
	private Date reportlossdate;// ???卡挂失时间
	@ApiModelProperty(value = "北京通号")
	private String bjtNo;// 北京通号
	@ApiModelProperty(value = "失败原因")
	private String failReason;// ???制卡失败原因
	@ApiModelProperty(value = "银行卡状态")
	private String bankStatus;// 银行卡状态
	@ApiModelProperty(value = "重制类型")
	private Integer resetType;// ???重制类型
	@ApiModelProperty(value = "重制状态")
	private Integer resetState;// ???重制状态
	@ApiModelProperty(value = "配送批次号")
	private String psno;// ???配送批次号
	@ApiModelProperty(value = "制卡状态")
	private Integer cardMakeState;// ???制卡状态

	// 银行卡信息
	@ApiModelProperty(value = "银行卡号")
	private String bankNo;// 银行卡号
	@ApiModelProperty(value = "银行卡激活时间")
	private Date bankactivatetime;// 银行卡激活时间
	@ApiModelProperty(value = "银行卡挂失时间")
	private Date banklogofftime;// 银行卡挂失时间
	@ApiModelProperty(value = "银行卡更新时间")
	private Date bankcardtime;// ???银行卡更新时间
	@ApiModelProperty(value = "银行卡挂失状态")
	private String losscard;// ???银行卡挂失状态
	@ApiModelProperty(value = "银行卡解挂状态")
	private String releaselosscard;// ???银行卡解挂状态
}
