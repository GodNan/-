
package com.css.bdpfnew.model.entity.bdpfnet;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @description
 * @createTime 2018年10月15日 下午2:32:57
 * @modifyTime 
 * @author HEYCH
 * @version 1.0
 */
@Entity
@Table(name = "BDPF_WEB_HOSPITAL",schema = "bdpfnet")
//@Table(name = "BDPF_WEB_HOSPITAL",schema = "bdpfnet2018")
@Data
public class NetHospital {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "assigned")
	@GeneratedValue(generator = "idGenerator")
	@Column(length = 32)
	private String uuid;
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
	private String areaCodeWeb;	//注册地址行政区划代码

	private Integer areaCode;	//注册地址行政区划代码

	private Integer isWorking;	//是否使用评残系统：1是；2不是

}
