package com.css.bdpfnew.model.entity.bdpfnew;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Where(clause = "flag = 1")
@Table(name = "D_CODE", schema = "bdpfnew2018")
public class DCode extends BaseEntity {

	private String code;

	private String description;

	private String remark;

	private Integer orderNum;

	private String typeCode;

	private Integer flag;

	@Transient
    public Long getId() {
        Long id = Long.parseLong(code);
        return id;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode == null ? null : typeCode.trim();
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}