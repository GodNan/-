package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Accessors
@Data
public class VoWebHospital {

	private String uuid; //uuid
	private String regXh;	//登记序号
	private String orgCode;	//组织机构代码
	private String orgName;	//医院名称
	private String[] evaluateTypes;	//医院评残项目
	private String orgAddress;	//经营地址
	private String telNo;	//联系电话
	private String areaCode;	//注册地址行政区划代码
	private Integer isWorking;	//是否使用
	private Set<String> areaCodeJiedao = new HashSet<String>();	//注册地址行政区划代码

}
