
package com.css.bdpfnew.model.entity.bdpfnew;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description TODO
 * @createTime 2018年10月15日 下午2:33:32
 * @modifyTime 
 * @author HEYCH
 * @version 1.0
 */
@Entity
@Table(name = "web_doctor", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class WebDoctor extends BaseEntityUuidAssigned {
	private static final long serialVersionUID = 5071774499838454825L;
	//private String uuid;
	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String hospitalId;	//医院表UUID
	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String citizenId;	//身份证号
	@Column(nullable = true, columnDefinition = "varchar2(100)")
	private String docName;		//姓名
	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String workHospital;	//所属医院（组织机构代码）
	@Column(nullable = true, columnDefinition = "varchar2(500)")
	private String workHospitalName;	//所属医院名称（组织机构代码）
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer workDepartment;		//所属科室
	@Column(nullable = true, columnDefinition = "varchar2(30)")
	private String evaluationType;		//医生评残项目
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer docTitle;	//医生职称
	@Column(nullable = true, columnDefinition = "varchar2(20)")
	private String mobilePho;	//手机号码
	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String userAccount;	//用户名
	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String userPassword;	//密码
	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String userIdcode;	//CA生成的唯一随机码
	@Column(nullable = true, columnDefinition = "varchar2(30)")
	private String hosEvalType;		//医院选择的评残类别
	@Column(nullable = true, columnDefinition = "varchar2(1000)")
	private String areaCode;	//行政区划代码（与医院的一致）

}
