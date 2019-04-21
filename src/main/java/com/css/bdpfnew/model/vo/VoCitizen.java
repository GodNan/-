package com.css.bdpfnew.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月25日 下午11:47:22
 * @Description:
 */
@Accessors
@Data
public class VoCitizen {

	// 基本信息
	private String uuid;
	private String requestId;
	private String requestIdCard;
	private String name;
	private String citizenId;//
	private Date birthdate;//
	private Integer gender;//
	private Integer race;//
	private Integer eduLevel;//
	private Integer marriager;//
	private Integer political;//
	private Integer hukouKind;//
	private String email;//
	private String mobilePhone;//
	private String phoneNo;//
	private Integer hadFinalReviewed;
	private Integer isMoving;

	// 户籍地居住地信息
	private String cityid;//
	private String jiguanCode;// 户籍区划
	private String zipcode;//
	private String cityidAddrstr;//
	private String residentCity;//
	private String residentAdd;//
	private String residentZipcode;//
	private String residentcityAddrstr;//

	// 联系人信息
	private String guardian;//
	private String guardianCitizenId;//
	private String guardianPhone;//
	private String guardianMobilePhone;//
	private Integer guardianGender;//
	private Integer guardianOrAgent;//
	private Integer guardianRelation;//

	// 残疾信息
	private String kindstr;

	private String cardNo;// 卡号

	// 公安部验证是否成功
	private Integer isGongan;
	private Integer isHulian;
	private String cardNum;

}
