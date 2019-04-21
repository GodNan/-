/**
 * 
 */
package com.css.bdpfnew.model.entity.bdpfnew.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @Author HEYCH
 * @Date 2018年10月4日 下午12:09:41
 * @Description
 */

@Entity
@Table(name = "CARD_FOR_APPLYS", schema = "bdpfnew2018")
@Data
public class CardForApplys implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	private String uuid;
	private String requestId;
	private String citizenId;
	private String name;
	private String cityid;
	private String jiguanCode;
	private Date firstCertDate;
	private Date firstgivecarddate;
	private Date createDate;
	private Date modifyDate;

}