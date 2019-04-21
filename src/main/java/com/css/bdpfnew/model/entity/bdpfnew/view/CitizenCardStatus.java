package com.css.bdpfnew.model.entity.bdpfnew.view;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "V_CITIZEN_CARD_STATUS", schema = "bdpfnew2018")
@Data
public class CitizenCardStatus implements Serializable {

	private static final long serialVersionUID = 4282063121120478287L;
	@Id
	private String uuid;
	private String requestId;
	private String name;
	private String cardNum;
	private Integer gender;
	private Integer idtKind;
	private String kindstr;
	private String cityidAddrstr;
	private Integer statusRecord;
	private String pcno;
	private String cardStatus;

	private Date createDate;
	private Date modifyDate;
	private String cityid;
	private String citizenId;
	private Integer idtLevel;
	private Integer eduLevel;
	private String race;
	private Integer marriager;
	private Integer hadGaveCard;
	private Integer hadFinalReviewed;
	private Date certDate;
	private String phoneNo;
	private String mobilePhone;

}
