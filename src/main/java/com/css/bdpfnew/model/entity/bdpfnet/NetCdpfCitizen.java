
package com.css.bdpfnew.model.entity.bdpfnet;

import com.css.bdpfnew.util.SM4;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @description TODO
 * @createTime 2018年10月15日 下午2:33:32
 * @modifyTime 
 * @author HEYCH
 * @version 1.0
 */
@Entity
@Table(name = "CDPF_CITIZEN",schema = "bdpfnet")
//@Table(name = "CDPF_CITIZEN",schema = "bdpfnet2018")
public class NetCdpfCitizen {

	private static final long serialVersionUID = 5071774499838454825L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "assigned")
	@GeneratedValue(generator = "idGenerator")
	@Column(length = 32)
	private String uuid;

	private Date createtime;

	private String citizenId;

	private Long cityid;

	private String jiguanCode;

	private String cityidAddrstr;

	private Integer businessId;

	private Integer processId;

	private String reviewStatus;

	private String name;

	private String gender;

	private String mobilePhone;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public Long getCityid() {
		return cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}

	public String getCityidAddrstr() {
		return cityidAddrstr;
	}

	public void setCityidAddrstr(String cityidAddrstr) {
		this.cityidAddrstr = cityidAddrstr;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public String getName() {
		String s = "";
		try {
			s = SM4.getStringAfterDecrypted(this.name);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getJiguanCode() {
		return jiguanCode;
	}

	public void setJiguanCode(String jiguanCode) {
		this.jiguanCode = jiguanCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
}
