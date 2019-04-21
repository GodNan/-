package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: Heych
 * @Date: 2018/7/25
 * @Version: 1.0
 * @Description:
 */

@ApiModel(value = "医院管理所需参数", description = "医院管理")
@Data
@Accessors(fluent = false)
public class DtoWebHospital {
	
	@ApiModelProperty(value = "uuid-修改功能必填")
	private String uuid;
	private String regXh;	//登记序号
	private String orgCode;	//组织机构代码
	private String orgName;	//医院名称
	private String[] evaluateTypes;	//医院评残项目
	private String orgAddress;	//经营地址
	private String telNo;	//联系电话
	private String areaCode;	//注册地址行政区划代码
	private Integer isWorking;	//是否使用评残系统
	private Set<String> areaCodeJiedao = new HashSet<String>();	//注册地址行政区划代码

}
