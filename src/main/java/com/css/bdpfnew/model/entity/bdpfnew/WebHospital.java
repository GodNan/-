
package com.css.bdpfnew.model.entity.bdpfnew;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description TODO
 * @createTime 2018年10月15日 下午2:32:57
 * @modifyTime 
 * @author HEYCH
 * @version 1.0
 */
@Entity
@Table(name = "web_hospital", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class WebHospital extends BaseEntityUuidAssigned {
	
	private static final long serialVersionUID = -4015794985160046994L;
	//private String uuid;
	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String regXh;	//登记序号
	@Column(nullable = true, columnDefinition = "varchar2(30)")
	private String orgCode;	//组织机构代码
	@Column(nullable = true, columnDefinition = "varchar2(1000)")
	private String orgName;	//医院名称
	@Column(nullable = true, columnDefinition = "varchar2(30)")
	private String evaluateType;	//医院评残项目
	@Column(nullable = true, columnDefinition = "varchar2(1000)")
	private String orgAddress;	//经营地址
	@Column(nullable = true, columnDefinition = "varchar2(50)")
	private String telNo;	//联系电话
	@Column(nullable = true, columnDefinition = "varchar2(1000)")
	private String areaCode;	//注册地址行政区划代码

	private Integer isWorking;	//是否使用评残系统：1是；2不是


}
