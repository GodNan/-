
package com.css.bdpfnew.model.entity.bdpfnet;

import com.css.bdpfnew.util.SM4;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author lvmn
 * @Date 2019-02-26 10:44:11
 * @Description
 * 	残疾人预约医院信息
 */
@Entity
@Table(name = "IDT_CITIZEN_KIND_HOSPITAL",schema = "bdpfnet")
//@Table(name = "IDT_CITIZEN_KIND_HOSPITAL",schema = "bdpfnet2018")
//@Data
public class NetIdtCitizenKindHospital {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "assigned")
	@GeneratedValue(generator = "idGenerator")
	@Column(length = 32)
	private String uuid;

	private String businessId;

	private String citizenId;

	private String mobilePhone;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;

	private String hospital;

	private String idtRecordUuid;

	private String idtRecordUuidReview;

	private BigDecimal idtkind;

	@Temporal(TemporalType.DATE)
	private Date idttime;

	@Temporal(TemporalType.DATE)
	private Date bookingtime;

	private String name;

	private String jiguanCode;

	private BigDecimal reviewStatus;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getIdtRecordUuid() {
		return idtRecordUuid;
	}

	public void setIdtRecordUuid(String idtRecordUuid) {
		this.idtRecordUuid = idtRecordUuid;
	}

	public String getIdtRecordUuidReview() {
		return idtRecordUuidReview;
	}

	public void setIdtRecordUuidReview(String idtRecordUuidReview) {
		this.idtRecordUuidReview = idtRecordUuidReview;
	}

	public BigDecimal getIdtkind() {
		return idtkind;
	}

	public void setIdtkind(BigDecimal idtkind) {
		this.idtkind = idtkind;
	}

	public Date getIdttime() {
		return idttime;
	}

	public void setIdttime(Date idttime) {
		this.idttime = idttime;
	}

	public Date getBookingtime() {
		return bookingtime;
	}

	public void setBookingtime(Date bookingtime) {
		this.bookingtime = bookingtime;
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

	public BigDecimal getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(BigDecimal reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getJiguanCode() {
		return jiguanCode;
	}

	public void setJiguanCode(String jiguanCode) {
		this.jiguanCode = jiguanCode;
	}
}
