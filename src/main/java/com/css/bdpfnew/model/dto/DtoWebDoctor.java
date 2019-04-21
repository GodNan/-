package com.css.bdpfnew.model.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther: Heych
 * @Date: 2018/7/25
 * @Version: 1.0
 * @Description:
 */

@ApiModel(value = "医生管理所需参数", description = "医生管理")
@Data
@Accessors(fluent = false)
public class DtoWebDoctor {
	
	@ApiModelProperty(value = "uuid-修改功能必填")
	private String uuid;
	private String hospitalId;	//医院表UUID
	private String citizenId;	//身份证号
	private String docName;		//姓名
	private String workHospital;	//所属医院（组织机构代码）
	private String workHospitalName; //所属医院名称
	private Integer workDepartment;		//所属科室
	private String evaluationType;		//医生评残项目
	private Integer docTitle;	//医生职称
	private String mobilePho;	//手机号码
	private String userAccount;	//用户名
	private String userPassword;	//密码
	private String userIdcode;	//CA生成的唯一随机码
	private String hosEvalType;		//医院选择的评残类别
	private String areaCode;	//行政区划代码（与医院的一致）
    
}
