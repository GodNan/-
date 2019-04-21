package com.css.bdpfnew.model.entity.bdpfnew;


import org.hibernate.annotations.Where;
import javax.persistence.*;



@Entity
@Where(clause = "flag = 1")
@Table(name = "D_CODE_TYPE", schema = "bdpfnew2018")
public class DCodeType extends BaseEntity{
    private String typeCode;
    private String  description;
    private String  remark;
    private Integer  flag;
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
    

   
}
