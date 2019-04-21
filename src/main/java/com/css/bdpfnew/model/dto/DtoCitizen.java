package com.css.bdpfnew.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月25日 下午10:51:47
 * @Description:
 */
@ApiModel(value = "残疾人新增所需参数", description = "新证申请残疾人提交")
@Data
@Accessors(fluent = false)
public class DtoCitizen {

	// 基本信息
	@ApiModelProperty(value = "uuid-修改功能必填")
	private String uuid;

	@ApiModelProperty(value = "任务主键")
	private String requestId;

//	@ApiModelProperty(value = "卡任务主键")
//	private String requestIdCard;

	@ApiModelProperty(value = "姓名", required = true)
	@NotNull(message = "请填写姓名")
	private String name;
	@ApiModelProperty(value = "身份证号", required = true)
	@NotNull(message = "请填写身份证号")
	private String citizenId;//
	@ApiModelProperty(value = "出生日期", required = true)
	@NotNull(message = "出生日期不能为空")
	private Date birthdate;//

	@ApiModelProperty(value = "性别", required = true)
	@NotNull(message = "请选择性别")
	private Integer gender;//
	@ApiModelProperty(value = "名族", required = true)
	@NotNull(message = "请选择名族")
	private Integer race;//
	@ApiModelProperty(value = "文化程度", required = true)
	@NotNull(message = "请选择文化程度")
	private Integer eduLevel;//
	@ApiModelProperty(value = "婚姻状况", required = true)
	@NotNull(message = "请选择婚姻状况")
	private Integer marriager;//
	@ApiModelProperty(value = "政治面貌", required = true)
	@NotNull(message = "请选择政治面貌")
	private Integer political;//
	@ApiModelProperty(value = "户口类别", required = true)
	@NotNull(message = "请选择户口类别")
	private Integer hukouKind;//
	@ApiModelProperty(value = "邮箱")
	private String email;//

	@ApiModelProperty(value = "移动电话", required = true)
	@NotNull(message = "请填写移动电话")
	private String mobilePhone;//
	@ApiModelProperty(value = "电话")
	private String phoneNo;//
//	@ApiModelProperty(value = "一卡通号")
//	private String cardNo;//

	// 户籍地居住地信息
	@ApiModelProperty(value = "户籍区划id", required = true)
	@NotNull(message = "请选择户籍区划")
	private String cityid;//
	@ApiModelProperty(value = "户籍区划", required = true)
	@NotNull(message = "户籍区划不能为空")
	private String jiguanCode;// 户籍区划
	@ApiModelProperty(value = "户籍邮编", required = true)
	@NotNull(message = "请填写户籍邮编")
	private String zipcode;//
	@ApiModelProperty(value = "户籍地详细地址", required = true)
	@NotNull(message = "请填写户籍地详细地址")
	private String cityidAddrstr;//
	@ApiModelProperty(value = "居住区划id", required = true)
	@NotNull(message = "请选择居住区划")
	private String residentCity;//
	@ApiModelProperty(value = "居住区划", required = true)
	@NotNull(message = "居住区划不能为空")
	private String residentAdd;//
	@ApiModelProperty(value = "居住邮编", required = true)
	@NotNull(message = "请填写居住邮编")
	private String residentZipcode;//
	@ApiModelProperty(value = "居住地详细地址", required = true)
	@NotNull(message = "请填写居住地详细地址")
	private String residentcityAddrstr;//

	// 联系人信息
	@ApiModelProperty(value = "联系人姓名", required = true)
	@NotNull(message = "请填写联系人姓名")
	private String guardian;//
	@ApiModelProperty(value = "联系人身份证号", required = true)
	@NotNull(message = "请填写联系人身份证号")
	private String guardianCitizenId;//
	@ApiModelProperty(value = "联系人电话")
	private String guardianPhone;//
	@ApiModelProperty(value = "联系人手机", required = true)
	@NotNull(message = "请填写联系人手机")
	private String guardianMobilePhone;//
	@ApiModelProperty(value = "联系人性别", required = true)
	@NotNull(message = "请选择联系人性别")
	private Integer guardianGender;//
	@ApiModelProperty(value = "联系人类型", required = true)
	@NotNull(message = "请选择联系人类型")
	private Integer guardianOrAgent;//
	@ApiModelProperty(value = "与联系人关系", required = true)
	@NotNull(message = "请选择与联系人关系")
	private Integer guardianRelation;

}
